package ru.savvy.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.savvy.Main;
import ru.savvy.entity.Course;
import ru.savvy.entity.course.CourseMapped;
import ru.savvy.entity.course.Level;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class IntegrationTest {

    @Autowired
    private TestService testService;

    @PersistenceContext
    EntityManager em;


    private void checkCoursesQuantity(final int quantity){
        String jpql = "select c from Course c";
        Query q = em.createQuery(jpql);
        List<Course> courses = q.getResultList();
        Assert.assertEquals(quantity, courses.size());
    }

    private Level makeLevel(int number){
        Level level = new Level();
        level.setName("Level " + number);
        return level;
    }

    private Course makeCourse(String name, int levelsCount){
        Course course = new Course();
        course.setName(name);
        CourseMapped courseMapped = new CourseMapped();
        courseMapped.setName(name);
        course.setCourseMapped(courseMapped);
        course.getCourseMapped().setLevels(new ArrayList<>());
        for(int i = 0; i < levelsCount; i++){
            course.getCourseMapped().getLevels().add(makeLevel(i));
        }
        return course;
    }


    /**
     * Delete all courses
     */
    @Test
    public void test1() throws Exception {
        String jpql = "delete from Course";
        Query q = em.createQuery(jpql);
        q.executeUpdate();
        checkCoursesQuantity(0);
    }

    /**
     * Create new courses
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Course course1 = makeCourse("First one", 2);
        Course course2 = makeCourse("Second one", 3);
        Course course3 = makeCourse("Third one", 0);

        em.persist(course1);
        em.persist(course2);
        em.persist(course3);

        em.flush();
        checkCoursesQuantity(3);
    }

    /**
     * example of native SQL usage within JPQL
     * select all courses having name set to 'Second one'
     */
    @Test
    public void test3() throws Exception {
        String jpql = "SELECT c FROM Course c where SQL('course_mapped ->> ''?'' = ''Second one''',c.name) ";
        Query q = em.createQuery(jpql, Course.class);
        List<Course> courses = q.getResultList();
        Assert.assertEquals(1, courses.size());
        Assert.assertEquals("Second one", courses.get(0).getCourseMapped().getName());

    }

    /**
     * More complex example of combining postgres functions and operators
     * select all courses containing 'i' and having at least one level
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        String jpql = "SELECT c FROM Course c where SQL('course_mapped ->> ''?'' LIKE ''%i%''', c.name) AND " +
                "SQL('json_array_length(course_mapped #> ''{levels}'') > 0')";
        Query q = em.createQuery(jpql, Course.class);
        List<Course> courses = q.getResultList();
        Assert.assertEquals(1,courses.size());
        Assert.assertEquals("First one", courses.get(0).getCourseMapped().getName());
    }

    /**
     * Tricky modification of mapped object test
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        String jpql = "SELECT c FROM Course c where c.name = :name";
        TypedQuery<Course> q = em.createQuery(jpql, Course.class);
        q.setParameter("name", "Second one");
        Course course = q.getSingleResult();
        CourseMapped courseMapped = course.getCourseMapped();
        course.setName("Edited one");
        em.merge(course);
        em.flush();

        courseMapped.setName("Edited one");
        courseMapped.getLevels().remove(0); // and remove one level
        // commit current transaction, we gonna test results in another one
        Assert.assertTrue(true);
    }

    /**
     * Here we check the results of the previous test
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        String jpql = "SELECT c FROM Course c where c.name = :name";
        TypedQuery<Course> q = em.createQuery(jpql, Course.class);
        q.setParameter("name", "Edited one");
        Course course = q.getSingleResult();
        Assert.assertEquals("Edited one", course.getCourseMapped().getName());
        Assert.assertEquals(2, course.getCourseMapped().getLevels().size());
    }


}
