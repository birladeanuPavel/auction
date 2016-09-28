package com.birladeanu.dal.model;

import com.birladeanu.dal.model.parent.MainModel;
import lombok.*;

import javax.persistence.*;

/**
 * Created by pavel on 9/28/16.
 */
@Entity
@Data
@EqualsAndHashCode(exclude = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Shipment extends MainModel {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ITEM_SHIPPMENT",
            joinColumns = @JoinColumn(name = "SHIPPMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID", nullable = false, unique = true)
    )
    protected Item item;

}
