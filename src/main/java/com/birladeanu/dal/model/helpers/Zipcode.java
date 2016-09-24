package com.birladeanu.dal.model.helpers;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by pavel on 9/10/16.
 */
@Getter
@EqualsAndHashCode
public abstract class Zipcode implements Serializable{

    protected String value;

    public Zipcode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
