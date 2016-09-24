package com.birladeanu.dal.model.parent;

import com.birladeanu.dal.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by pavel on 9/12/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public abstract class BillingDetails extends MainModel {

    @NotNull
    protected String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    protected User user;

    protected BillingDetails() {
    }

    public BillingDetails(String owner) {
        this.owner = owner;
    }

}
