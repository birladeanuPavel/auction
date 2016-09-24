package com.birladeanu.dal.model.subselect;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by pavel on 8/30/16.
 */
@Entity
@org.hibernate.annotations.Immutable
@org.hibernate.annotations.Subselect(
        value = "select i.id as ITEMID, i.name as NAME, " +
                "count(b.id) as NUMBEROFBIDS " +
                "from Item i left outer join Bid b on i.id = b.item_id " +
                "group by i.id, i.name"
)
@org.hibernate.annotations.Synchronize({"Item", "Bid"})
@Getter
public class ItemBidSummary {

    @Id
    protected Long itemId;

    protected String name;

    protected long numberOfBids;

}
