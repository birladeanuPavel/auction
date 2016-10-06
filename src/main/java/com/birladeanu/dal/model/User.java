package com.birladeanu.dal.model;

import com.birladeanu.dal.model.embedabble.Address;
import com.birladeanu.dal.model.parent.BillingDetails;
import com.birladeanu.dal.model.parent.MainModel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pavel on 9/10/16.
 */
@Entity(name = "USERS")
@Data
@Table(
        name = "USERS",
        uniqueConstraints =
        @UniqueConstraint(
                name = "UNQ_USERNAME_EMAIL",
                columnNames = { "USERNAME", "EMAIL" }
        ),
        indexes = {
                @Index(
                        name = "IDX_USERNAME",
                        columnList = "USERNAME"
                ),
                @Index(
                        name = "IDX_USERNAME_EMAIL",
                        columnList = "USERNAME, EMAIL"
                )
        }
)
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
public class User extends MainModel {

    @Embedded
    protected Address homeAddress;

    @NonNull
    @Setter
    protected String userName;

    @NonNull
    @Setter
    protected String email;

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
