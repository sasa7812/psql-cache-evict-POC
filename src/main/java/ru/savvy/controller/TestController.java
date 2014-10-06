package ru.savvy.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.savvy.entity.Course;
import ru.savvy.entity.User;
import ru.savvy.entity.UserProfile;
import ru.savvy.repository.CourseRepository;
import ru.savvy.repository.UserProfileRepository;
import ru.savvy.repository.UserRepository;
import ru.savvy.service.CourseFacade;

import java.util.List;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */

@RestController
@RequestMapping(value = "/")
public class TestController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseFacade courseFacade;

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "")
    public String helloString(){
        return "Hello!";
    }

    @RequestMapping(value = "test")
    public String testVersioning(){
//        User user = new User();
//        user.setName("Sasa");
//        user.setLogin("sasa");
//        user.setPassword("123");
//        userRepository.saveAndFlush(user);
//        user = userRepository.findAll().get(0);
//
//        UserProfile userProfile = new UserProfile();
//        userProfile.setDescription("New description");
//        userProfile.setCreatedDate(new DateTime());
//        userProfile.setCreatedBy(user);
//        userProfileRepository.saveAndFlush(userProfile);
//        userProfile = userProfileRepository.findAll().iterator().next();
//        userProfile.setDescription("Corrected description");
//        userProfileRepository.save(userProfile);
        courseFacade.createCourse();
        return "Done!";
    }

    @RequestMapping(value = "readtest")
    public List<Course> testJsonRead(){
        return courseRepository.findAll();
    }

    @RequestMapping(value = "updatetest")
    public String updateCourse(){
        return "Course updated id: " + courseFacade.updateCourse();
    }
}
