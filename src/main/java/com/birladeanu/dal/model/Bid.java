package com.birladeanu.dal.model;

import com.birladeanu.dal.model.parent.MainModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
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
@Immutable
@EqualsAndHashCode
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

    public Bid() {
    }

    public Bid(Item item) {
        this.item = item;
        item.getBids().add(this);
    }

}
