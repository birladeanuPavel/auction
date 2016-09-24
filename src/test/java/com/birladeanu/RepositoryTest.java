package com.birladeanu;

import com.birladeanu.dal.model.*;
import com.birladeanu.dal.model.helpers.Currency;
import com.birladeanu.dal.model.helpers.MonetaryAmount;
import com.birladeanu.dal.model.Bid;
import com.birladeanu.dal.repository.BidRepository;
import com.birladeanu.dal.repository.ItemRepository;
import com.birladeanu.dal.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by pavel on 9/1/16.
 */
public class RepositoryTest extends AbstractTest {

    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testItemBidRepository() throws Exception {
        Item item = new Item();
        item.setName("Chair");
        Bid bid = new Bid(item);
        bid.setAmount(BigDecimal.TEN);
        bid.setCreatedOn(new Date());
        itemRepository.save(item);
        bidRepository.save(bid);
        assertNotNull(item.getId());
        assertNotNull(bid.getId());
    }

    @Test
    public void testBidRepository() throws Exception {
        Item item = new Item();
        item.setName("Chair");
        item.setDescription("Some long description");
        item.setBuyNowPrice(new MonetaryAmount(BigDecimal.TEN, new Currency("USD")));
        item.setInitialPriceWithCurrency(new MonetaryAmount(BigDecimal.TEN, new Currency("CHF")));
        Bid bid = new Bid(item);
        bid.setAmount(BigDecimal.TEN);
        bid.setCreatedOn(new Date());
        itemRepository.save(item);
        bidRepository.save(bid);
        Bid bidFoundByItemName = bidRepository.findByItemName(item.getName());
        assertNotNull(bidFoundByItemName);
        assertThat(bidFoundByItemName.getAmount(), is(bid.getAmount()));
        Bid bidFoundByItem = bidRepository.findByItem(item);
        assertNotNull(bidFoundByItem);
        assertThat(bidFoundByItem.getAmount(), is(bid.getAmount()));
    }

}
