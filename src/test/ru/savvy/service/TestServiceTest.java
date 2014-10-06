package ru.savvy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.savvy.Main;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    public void test1() throws Exception {
        testService.clearCourses();
        testService.createEntity();
        testService.updateEntityWithFlush();
    }


}
