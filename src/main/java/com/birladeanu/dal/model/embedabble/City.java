package com.birladeanu.dal.model.embedabble;

import com.birladeanu.dal.model.converter.ZipcodeConverter;
import com.birladeanu.dal.model.helpers.Zipcode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Created by pavel on 9/10/16.
 */
@Embeddable
@Getter
@EqualsAndHashCode
@ToString
public class City {

    @NotNull
    @Column(nullable = false)
    protected String name;

    @NotNull
    @Column(nullable = false, length = 5)
    @Convert(converter = ZipcodeConverter.class)
    protected Zipcode zipcode;

    @NotNull
    @Column(nullable = false)
    protected String country;

    protected City() {
    }

    public City(String name, Zipcode zipcode, String country) {
        this.name = name;
        this.zipcode = zipcode;
        this.country = country;
    }

}
