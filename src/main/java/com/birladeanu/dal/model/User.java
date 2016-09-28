package com.birladeanu.dal.model;

import com.birladeanu.dal.model.embedabble.Address;
import com.birladeanu.dal.model.parent.BillingDetails;
import com.birladeanu.dal.model.parent.MainModel;
import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pavel on 9/10/16.
 */
@Entity(name = "USERS")
@Data
public class User extends MainModel {

    @Embedded
    protected Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",
                    column = @Column(name = "BILLING_STREET")),
            @AttributeOverride(name = "city.zipcode",
                    column = @Column(name = "BILLING_ZIPCODE", length = 5)),
            @AttributeOverride(name = "city.name",
                    column = @Column(name = "BILLING_CITY")),
            @AttributeOverride(name = "city.country",
                    column = @Column(name = "BILLING_COUNTRY"))
    })
    protected Address billingAdress;

    @OneToOne(
//            fetch = FetchType.LAZY,
//            optional = false,
            mappedBy = "user",
            cascade = CascadeType.PERSIST)
    protected ShippingAddress shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    protected BillingDetails defaultBilling;

    @OneToMany(mappedBy = "user")
    protected Set<BillingDetails> billingDetails = new HashSet<>();

    protected User() {
    }

    public User(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

}
