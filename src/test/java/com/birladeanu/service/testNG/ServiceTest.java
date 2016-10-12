package com.birladeanu.service.testNG;

import com.birladeanu.TestDataProvider;
import com.birladeanu.config.AppConfig;
import com.birladeanu.dal.model.Item;
import com.birladeanu.dal.model.User;
import com.birladeanu.service.ItemService;
import com.birladeanu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.OptimisticLockException;

/**
 * Created by pavel on 10/6/16.
 */
@ContextConfiguration(classes = AppConfig.class)
public class ServiceTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    private Item item;
    private User user;

    @BeforeClass
    public void before(){
        item = TestDataProvider.createSimpleItem();
        itemService.save(item);
        user = TestDataProvider.createSimpleUser();
        userService.save(user);
    }

    @Test(threadPoolSize = 2, invocationCount = 9,
            expectedExceptions = {OptimisticLockException.class,
                    ObjectOptimisticLockingFailureException.class})
    public void itemService() {
        item.setDescription("First");
        itemService.save(item);
    }

    @Test(threadPoolSize = 8, invocationCount = 50,
            expectedExceptions = {OptimisticLockException.class,
                    ObjectOptimisticLockingFailureException.class}
    )

    public void userService() {
        user.setEmail("AnotherEmail");
        userService.save(user);
    }

}
