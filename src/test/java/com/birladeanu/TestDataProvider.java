package com.birladeanu;

import com.birladeanu.dal.model.*;
import com.birladeanu.dal.model.embedabble.Address;
import com.birladeanu.dal.model.embedabble.City;
import com.birladeanu.dal.model.helpers.Currency;
import com.birladeanu.dal.model.helpers.GermanZipcode;
import com.birladeanu.dal.model.helpers.MonetaryAmount;
import com.birladeanu.dal.model.helpers.SwissZipcode;
import com.birladeanu.dal.model.parent.BillingDetails;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pavel on 10/7/16.
 */
public class TestDataProvider {

    public static Item createSimpleItem() {
        Item item = new Item();
        item.setName("Chair");
        item.setBuyNowPrice(new MonetaryAmount(BigDecimal.TEN, new Currency("CHF")));
        item.setInitialPriceWithCurrency(new MonetaryAmount(BigDecimal.TEN, new Currency("USD")));
        item.setAuctionStart(new Date());
        item.setAuctionEnd(new Date());
        return item;
    }

    public static User createSimpleUser() {
        City homeCity = new City("homeCity", new GermanZipcode("12342"), "homeCountry");
        Address homeAddress = new Address("someStree", homeCity);
        City billingCity = new City("billinbCity", new SwissZipcode("5415"), "billingCountry");
        Address billingAddress = new Address("someStree", billingCity);

        User user = new User(homeAddress);
        user.setUserName("test");
        user.setEmail("test@mail.ru");
        ShippingAddress shippingAddress = new ShippingAddress(user);
        shippingAddress.setCity("TestCity");
        shippingAddress.setZipcode("Testzipcode");
        shippingAddress.setStreet("Teststreet");
        user.setShippingAddress(shippingAddress);
        user.setBillingAdress(billingAddress);

        return user;
    }

}
