package com.birladeanu.dal.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by pavel on 9/28/16.
 */
@Entity
@Getter
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
public class ShippingAddress {

    @Id
    @GeneratedValue(generator = "addressKeyGenerator")
    @org.hibernate.annotations.GenericGenerator(
            name = "addressKeyGenerator",
            strategy = "foreign",
            parameters =
            @org.hibernate.annotations.Parameter(
                    name = "property", value = "user"
            )
    )
    protected Long id;

    @Setter
    @NotNull
    @Column(nullable = false)
    protected String zipcode;

    @Setter
    @NotNull
    @Column(nullable = false)
    protected String street;

    @Setter
    @NotNull
    @Column(nullable = false)
    protected String city;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    protected User user;

    protected ShippingAddress() {
    }

    public ShippingAddress(User user) {
        this.user = user;
    }

}
