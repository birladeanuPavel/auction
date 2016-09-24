package com.birladeanu.dal.repository;

import com.birladeanu.dal.model.Bid;
import com.birladeanu.dal.model.Item;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by pavel on 9/1/16.
 */
public interface BidRepository extends MainRepository<Bid, Long> {

    @Query(value = "Select b from Bid b where b.item.name = ?1")
    Bid findByItemName(String itemName);

    Bid findByItem(Item item);
}
