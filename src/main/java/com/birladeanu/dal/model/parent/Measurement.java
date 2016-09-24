package com.birladeanu.dal.model.parent;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Created by pavel on 9/12/16.
 */
@MappedSuperclass
@Getter
public abstract class Measurement {

    @NotNull
    protected String name;
    @NotNull
    protected String symbol;

    protected Measurement(){}

    public Measurement(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}
