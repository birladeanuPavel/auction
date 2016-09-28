package com.birladeanu.dal.model;

import com.birladeanu.dal.model.converter.MonetaryAmountConverter;
import com.birladeanu.dal.model.embedabble.Dimension;
import com.birladeanu.dal.model.embedabble.Filename;
import com.birladeanu.dal.model.embedabble.ItemImage;
import com.birladeanu.dal.model.embedabble.Weight;
import com.birladeanu.dal.model.enums.AuctionTypeEnum;
import com.birladeanu.dal.model.helpers.MonetaryAmount;
import com.birladeanu.dal.model.parent.MainModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.OrderBy;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

import static com.birladeanu.dal.model.enums.AuctionTypeEnum.HIGHEST_BID;

/**
 * Created by pavel on 8/28/16.
 */
@Entity
@Getter
@EqualsAndHashCode(exclude = "bids, stringSetImages, stringImageBag, stringImageList, stringImageMap, itemImages")
public class Item extends MainModel {

    @NotNull
    @Size(min = 2, max = 255, message = "Name is required, maximum 255 characters")
    @Column(nullable = false, updatable = false)
    @Setter
    protected String name;

    @Setter
    protected String description;

    @Formula("concat(substr(DESCRIPTION, 1, 12), '...')")
    @Setter
    protected String shortDescription;

    @Formula("(Select avg(b.amount) from Bid b where b.item_id = id)")
    @Setter
    protected BigDecimal averageBidAmount;

    @Column(name = "IMPERIALWEIGHT")
    @ColumnTransformer(read = "IMPERIALWEIGHT / 2.20462", write = "? * 2.20462")
    @Setter
    protected double metricWeight;

//    @Temporal(value = TemporalType.TIMESTAMP)
//    @Column(updatable = false, insertable = false)
//    @Generated(GenerationTime.ALWAYS)
//    protected Date lastModified;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    protected Date createdOn;

    @Column(insertable = false)
    @ColumnDefault(value = "1.00")
    @Generated(GenerationTime.INSERT)
    @Setter
    protected BigDecimal initialPrice;

    @NotNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Setter
    protected AuctionTypeEnum auctionTypeEnum = HIGHEST_BID;

    @Type(type = "yes_no")
    @Setter
    protected boolean verified;

    @NotNull
    @Convert(converter = MonetaryAmountConverter.class)
    @Column(name = "PRICE", length = 63)
    @Setter
    protected MonetaryAmount buyNowPrice;

    @NotNull
    @Type(type = "monetary_amount_chf")
    @Columns(columns = {
        @Column(name = "INITIALPRICE_AMOUNT"),
        @Column(name = "INITIALPRICE_CURRENCY", length = 3)
    })
    @Setter
    protected MonetaryAmount initialPriceWithCurrency;

    @Setter
    protected Weight weight;

    @Setter
    protected Dimension dimension;

    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Set<Bid> bids = new HashSet<>();

//    String image mappings [Just for study]
    @ElementCollection
    @CollectionTable(
            name = "STRING_IMAGE_SET",
            joinColumns = @JoinColumn(name = "ITEM_ID")
    )
    @Column(name = "FILENAME")
    @Setter
    protected Set<String> stringSetImages = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "STRING_IMAGE_BAG")
    @Column(name = "FILENAME")
    @CollectionId(
            columns = @Column(name = "IMAGE_ID"),
            type = @Type(type = "long"),
            generator = "ID_GENERATOR"
    )
    @OrderBy(clause = "FILENAME desc")
    @Setter
    protected Collection<String> stringImageBag = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "STRING_IMAGE_LIST")
    @OrderColumn
    @Column(name = "FILENAME")
    @Setter
    protected List<String> stringImageList;

    @ElementCollection
    @CollectionTable(name = "STRING_IMAGE_MAP")
    @MapKeyColumn(name = "FILENAME")
    @Column(name = "IMAGENAME")
    @Setter
    protected Map<String, String> stringImageMap = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @javax.persistence.OrderBy("filename, width DESC")
    @Setter
    protected Set<ItemImage> itemImages = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "IMAGE_MAP")
    @Setter
    protected Map<Filename, ItemImage> imagesMap = new HashMap<>();

//    One to one
    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "item",
            cascade = CascadeType.PERSIST
    )
    @Setter
    protected Shipment shipment;

}
