package com.birladeanu.dal.model;

import com.birladeanu.dal.model.parent.MainModel;
import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pavel on 8/28/16.
 */
@Entity
//bidder will be loaded eager as well his defaultBilling and billingDetails entities
@NamedEntityGraphs(
        @NamedEntityGraph(
                name = "BidBidder",
                attributeNodes = {
                        @NamedAttributeNode(
                                value = "bidder",
                                subgraph = "BidderBilling"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "BidderDefaultBilling",
                                attributeNodes = {
                                        @NamedAttributeNode("defaultBilling"),
                                        @NamedAttributeNode("billingDetails")
                                }
                        )
                }
        )
)
@Immutable
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(exclude = "item")
@ToString
@Getter
public class Bid extends MainModel {

    @NotNull
    @Column(nullable = false)
    @Setter
    protected BigDecimal amount;

    @Past
    @NotNull
    @Column(nullable = false, updatable = false)
    @Setter
    protected Date createdOn;

    @NotNull
    @ManyToOne(optional = false)
//    @JoinColumn
    protected Item item;

    @Setter
    @ManyToOne
    protected User bidder;

    public Bid(Item item) {
        this.item = item;
        item.getBids().add(this);
    }

}
