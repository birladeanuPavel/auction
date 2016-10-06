package com.birladeanu;

import com.birladeanu.config.AppConfig;
import com.birladeanu.dal.model.Item;
import com.birladeanu.dal.model.helpers.Currency;
import com.birladeanu.dal.model.helpers.MonetaryAmount;
import com.birladeanu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.OptimisticLockException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pavel on 10/6/16.
 */
@ContextConfiguration(classes = AppConfig.class)
public class ServiceTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    private ItemService itemService;

    private Item item;

    @BeforeClass
    public void before(){
        item = createSimpleItem();
        itemService.save(item);
    }

    @Test(threadPoolSize = 2, invocationCount = 9,
            expectedExceptions = {OptimisticLockException.class,
                    ObjectOptimisticLockingFailureException.class})
    public void itemService() {
        item.setDescription("First");
        itemService.save(item);
    }

    private Item createSimpleItem() {
        Item item = new Item();
        item.setName("Chair");
        item.setBuyNowPrice(new MonetaryAmount(BigDecimal.TEN, new Currency("CHF")));
        item.setInitialPriceWithCurrency(new MonetaryAmount(BigDecimal.TEN, new Currency("USD")));
        item.setAuctionStart(new Date());
        item.setAuctionEnd(new Date());
        return item;
    }
}
