package com.birladeanu;

import com.birladeanu.dal.model.Item;
import com.birladeanu.dal.model.helpers.Currency;
import com.birladeanu.dal.model.helpers.MonetaryAmount;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pavel on 8/28/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
@Transactional
public abstract class AbstractTest {
    protected Item createSimpleItem() {
        Item item = new Item();
        item.setName("Chair");
        item.setBuyNowPrice(new MonetaryAmount(BigDecimal.TEN, new Currency("CHF")));
        item.setInitialPriceWithCurrency(new MonetaryAmount(BigDecimal.TEN, new Currency("USD")));
        item.setAuctionStart(new Date());
        item.setAuctionEnd(new Date());
        return item;
    }
}
