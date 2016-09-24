package com.birladeanu.dal.model.embedabble;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Created by pavel on 9/10/16.
 */
@Embeddable
@Getter
@EqualsAndHashCode
@ToString
public class Address {

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @AttributeOverrides(
            @AttributeOverride(
                    name = "name",
                    column = @Column(name = "city", nullable = false)
            )
    )
    protected City city;

    protected Address() {
    }

    public Address(String street, City city) {
        this.street = street;
        this.city = city;
    }
}
