package ru.savvy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.savvy.entity.Course;
import ru.savvy.entity.course.Block;
import ru.savvy.entity.course.CourseMapped;
import ru.savvy.entity.course.Lesson;
import ru.savvy.entity.course.Level;
import ru.savvy.repository.CourseRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */
@Service
public class CourseFacade {

    @Autowired
    CourseRepository courseRepository;

    public void createCourse(){
        // --- lessons
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("Another one lesson", new Date(), 1000l));
        lessons.add(new Lesson("Another one lesson", new Date(), 1000l));
        lessons.add(new Lesson("Another one lesson", new Date(), 1000l));

        // --- blocks
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block("Another one block", lessons));
        blocks.add(new Block("Another one block", lessons));

        // --- levels
        List<Level> levels = new ArrayList<>();
        levels.add(new Level("Another one level",blocks));

        // --- course
        Course course = new Course();
        CourseMapped courseMapped = new CourseMapped();
        courseMapped.setName("This is another one course");
        courseMapped.setBeginsAt(new Date());
        courseMapped.setEndsAt(new Date());
        courseMapped.setLevels(levels);

        course.setCourseMapped(courseMapped);

        courseRepository.saveAndFlush(course);
    }
}
