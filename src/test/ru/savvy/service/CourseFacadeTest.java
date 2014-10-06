package ru.savvy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.savvy.Main;
import ru.savvy.config.SpringConfiguration;
import ru.savvy.entity.Course;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CourseFacadeTest {

    @Autowired
    private CourseFacade courseFacade;


    @Test
    public void testCreateCourse() throws Exception {
        Long id = courseFacade.createCourse();
        assertNotNull(id);

    }

    @Test
    public void testUpdateCourse() throws Exception {

    }
}