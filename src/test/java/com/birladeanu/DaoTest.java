package com.birladeanu;

import com.birladeanu.dal.dao.GenericDao;
import com.birladeanu.dal.model.*;
import com.birladeanu.dal.model.embedabble.*;
import com.birladeanu.dal.model.helpers.Currency;
import com.birladeanu.dal.model.helpers.GermanZipcode;
import com.birladeanu.dal.model.helpers.MonetaryAmount;
import com.birladeanu.dal.model.helpers.SwissZipcode;
import com.birladeanu.dal.model.parent.BillingDetails;
import com.birladeanu.dal.model.subselect.ItemBidSummary;
import com.google.common.collect.Sets;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.birladeanu.dal.model.enums.AuctionTypeEnum.FIXED_PRICE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by pavel on 8/28/16.
 */
public class DaoTest extends AbstractTest {

    @Autowired
    private GenericDao genericDao;

    @Test
    public void testItemAndBid() {
        Item item = new Item();
        item.setName("Chair");
        item.setDescription("Some long description about item");
        item.setMetricWeight(2D);
        item.setAuctionTypeEnum(FIXED_PRICE);
        item.setVerified(true);
        item.setBuyNowPrice(new MonetaryAmount(BigDecimal.TEN, new Currency("CHF")));
        item.setInitialPriceWithCurrency(new MonetaryAmount(BigDecimal.ONE, new Currency("RON")));
        item.setWeight(new Weight("Kilos", "KG", BigDecimal.TEN));
        item.setDimension(new Dimension("Length", "cm", BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
        item.setAuctionEnd(new Date());
        item.setAuctionStart(new Date());
        Bid bid = new Bid(item);
        bid.setAmount(BigDecimal.TEN);
        bid.setCreatedOn(new Date());
        genericDao.persist(item);
        item.setShipment(new Shipment(item));
        genericDao.persist(bid);
        assertNotNull(bid.getId());
        assertNotNull(item.getId());
        genericDao.getEntityManager().flush();
        genericDao.getEntityManager().refresh(item);

        Item fromDbItem = genericDao.getReference(Item.class, item.getId());
        Bid fromDbBid = fromDbItem.getBids().iterator().next();
        assertNotNull(fromDbItem);
        assertNotNull(fromDbBid);
        assertTrue(item.equals(fromDbItem));
        assertTrue(bid.equals(fromDbBid));
        assertTrue(fromDbItem.getAverageBidAmount().compareTo(BigDecimal.TEN) == 0);
        assertThat(fromDbItem.getShortDescription(), is("Some long de..."));
        assertTrue(item.getMetricWeight() == fromDbItem.getMetricWeight());
        assertNotNull(fromDbItem.getCreatedOn());
        assertTrue(fromDbItem.getAuctionTypeEnum() == FIXED_PRICE);
        assertTrue(item.isVerified());
        assertEquals(item.getInitialPriceWithCurrency().getCurrency(), new Currency("CHF"));
        assertNotNull(fromDbItem.getShipment());
        assertNotNull(fromDbItem.getShipment().getItem());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testNamedQuery() {
        Item item = createSimpleItem();
        genericDao.persist(item);
        Item item2 = new Item();
        item2.setName("Table");
        item2.setBuyNowPrice(new MonetaryAmount(BigDecimal.TEN, new Currency("CHF")));
        item2.setInitialPriceWithCurrency(new MonetaryAmount(BigDecimal.ZERO, new Currency("USD")));
        item2.setAuctionEnd(new Date());
        item2.setAuctionStart(new Date());
        genericDao.persist(item2);
        genericDao.getEntityManager().flush();

        List<Item> items = genericDao.createNamedQuery("findItemsOrderByName").
                getResultList();
        assertThat(items.size(), is(2));
        List<Item> itemsWitInitialPriceWithCurrency = genericDao.createNamedQuery("findItemBuyInitialPriceWithCurrencyGreaterThan")
                .setParameter("price", BigDecimal.ONE).getResultList();
        assertThat(itemsWitInitialPriceWithCurrency.size(), is(1));
    }

    @Test
    public void testItemBidSummary() {
        Item item = createSimpleItem();
        Bid bid = new Bid(item);
        bid.setAmount(BigDecimal.TEN);
        bid.setCreatedOn(new Date());
        genericDao.persist(item);
        genericDao.persist(bid);
        assertNotNull(bid.getId());
        assertNotNull(item.getId());
        genericDao.getEntityManager().flush();

        ItemBidSummary itemBidSummary = genericDao.find(ItemBidSummary.class, item.getId());
        assertNotNull(itemBidSummary);
        assertThat(itemBidSummary.getNumberOfBids(), is(1L));
        assertEquals(item.getName(), itemBidSummary.getName());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUser() {
        City homeCity = new City("homeCity", new GermanZipcode("12342"), "homeCountry");
        Address homeAddress = new Address("someStree", homeCity);
        City billingCity = new City("billinbCity", new SwissZipcode("5415"), "billingCountry");
        Address billingAddress = new Address("someStree", billingCity);
        BillingDetails defaultBilling = new CreditCard("Jown", "1234", "06", "06");
        BillingDetails firstBillDetail = new BankAccount("Owner", "123", "06", "15");
        BillingDetails secondBillDetail = new CreditCard("Owner", "123", "06", "15");

        User user = new User(homeAddress);
        user.setUserName("test");
        user.setEmail("test@mail.ru");
        ShippingAddress shippingAddress = new ShippingAddress(user);
        shippingAddress.setCity("TestCity");
        shippingAddress.setZipcode("Testzipcode");
        shippingAddress.setStreet("Teststreet");
        user.setShippingAddress(shippingAddress);
        user.setBillingAdress(billingAddress);
        user.setDefaultBilling(defaultBilling);
        user.getBillingDetails().add(firstBillDetail);
        user.getBillingDetails().add(secondBillDetail);

        firstBillDetail.setUser(user);
        secondBillDetail.setUser(user);

        genericDao.persist(user);
        genericDao.persist(firstBillDetail);
        genericDao.persist(secondBillDetail);
        genericDao.persist(defaultBilling);
        genericDao.getEntityManager().flush();
        genericDao.getEntityManager().refresh(user);

        CreditCard billingDetailsFromDb = genericDao.getEntityManager()
                .getReference(CreditCard.class, user.getDefaultBilling().getId());
        List<BillingDetails> billingDetailses =
                 genericDao.getEntityManager()
                        .createQuery("Select bd from BillingDetails bd where bd.user.id = :id")
                        .setParameter("id", user.getId()).getResultList();
        assertTrue(billingDetailsFromDb != null);
        assertThat(billingDetailses.size(), is(2));
        User savedUser = genericDao.getEntityManager().find(User.class, user.getId());
        assertNotNull(savedUser.getShippingAddress());
        assertThat(savedUser.getShippingAddress().getId(), is(user.getId()));
    }

    @Test
    public void testItemCollections() {
        Item item = createSimpleItem();
        item.setStringImageBag(Arrays.asList("D", "A", "B", "C"));
        item.setStringImageList(Arrays.asList("C", "B", "D"));
        item.setStringSetImages(Sets.newHashSet("A", "B", "C"));
        item.setStringImageMap(Maps.newHashMap("File", "SomeImg"));
        item.setItemImages(Sets.newHashSet(new ItemImage(item, "title", "someFile", 1, 1)));
        item.setImagesMap(Maps.newHashMap(new Filename("SomFileName", "jpg"),
                new ItemImage(item, "title", "someFile", 1, 1)));

        genericDao.persist(item);
        genericDao.getEntityManager().flush();
        Item savedItem = genericDao.find(Item.class, item.getId());
        assertNotNull(savedItem);
        assertThat(savedItem.getStringImageBag().size(), is(item.getStringImageBag().size()));
        assertThat(savedItem.getStringImageList().size(), is(item.getStringImageList().size()));
        assertThat(savedItem.getStringSetImages().size(), is(item.getStringSetImages().size()));
        assertThat(savedItem.getStringImageMap().size(), is(item.getStringImageMap().size()));
        assertThat(savedItem.getItemImages().size(), is(item.getItemImages().size()));
        assertThat(savedItem.getImagesMap().size(), is(item.getImagesMap().size()));
    }

}
