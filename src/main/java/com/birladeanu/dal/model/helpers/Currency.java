package com.birladeanu.dal.model.helpers;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by pavel on 9/10/16.
 */
@Getter
@EqualsAndHashCode
public class Currency implements Serializable {

    private String name;

    public Currency(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
