package com.birladeanu.dal.model;

import com.birladeanu.dal.model.embedabble.Address;
import com.birladeanu.dal.model.listener.UserListener;
import com.birladeanu.dal.model.parent.BillingDetails;
import com.birladeanu.dal.model.parent.MainModel;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pavel on 9/10/16.
 */
@Entity(name = "USERS")
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
//update only changed data
@org.hibernate.annotations.DynamicUpdate
@org.hibernate.annotations.BatchSize(size = 10)
@EntityListeners(UserListener.class)
@org.hibernate.envers.Audited
@Data
@ToString(exclude = {"billingDetails"})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
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
    @org.hibernate.envers.NotAudited
    protected Address billingAdress;

    @OneToOne(
//            fetch = FetchType.LAZY,
//            optional = false,
            mappedBy = "user",
            cascade = CascadeType.PERSIST)
    @org.hibernate.envers.NotAudited
    protected ShippingAddress shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @org.hibernate.envers.NotAudited
    protected BillingDetails defaultBilling;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    @org.hibernate.envers.NotAudited
    protected Set<BillingDetails> billingDetails = new HashSet<>();

    public User(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

}
