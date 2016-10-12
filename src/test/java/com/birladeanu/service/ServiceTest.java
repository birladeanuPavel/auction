package com.birladeanu.service;

import com.birladeanu.AbstractTest;
import com.birladeanu.TestDataProvider;
import com.birladeanu.dal.model.BankAccount;
import com.birladeanu.dal.model.User;
import com.birladeanu.dal.model.parent.BillingDetails;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by pavel on 10/12/16.
 */
public class ServiceTest extends AbstractTest{

    @Autowired
    private UserService userService;

    @Test
    @Rollback(value = false)
    public void userLazyBillingTest() {
//        BillingDetails firstBillDetail = new BankAccount("Owner", "123", "06", "15");
//
//        User user = TestDataProvider.createSimpleUser();
//        user.getBillingDetails().add(firstBillDetail);

//        userService.save(user);
//        assertNotNull(user.getId());
        User userFromDb = userService.findOne(1114L);
        assertThat(userFromDb.getBillingDetails().size(), is(0));
    }

}
