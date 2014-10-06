package ru.savvy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.savvy.entity.Course;
import ru.savvy.entity.course.CourseMapped;
import ru.savvy.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */

@Service
@Transactional
public class TestService {

    @Autowired
    private CourseFacade courseFacade;

    @Autowired
    private CourseRepository courseRepository;

    @PersistenceContext
    private EntityManager em;

    public void clearCourses(){
        courseRepository.deleteAll();
    }

    public void createEntity(){
        Course course = courseFacade.getNewCourse();
        em.persist(course);
    }

    public void updateEntity(){
        Course course = courseRepository.findAll().get(0); // for test only
        course.getCourseMapped().getLevels().remove(0);
        em.merge(course);
    }

    /**
     * Mike's example of loosing data after flush call
     */
    public void updateEntityWithFlush(){
        Course course = courseRepository.findAll().get(0); // for test only
        CourseMapped courseMapped = course.getCourseMapped();
        courseMapped.setName("Has been changed");
        em.flush();
        courseMapped.getLevels().remove(0);
    }
}

