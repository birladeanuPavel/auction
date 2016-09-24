package com.birladeanu.dal.model.helpers;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by pavel on 9/10/16.
 */
@Getter
@EqualsAndHashCode
public class MonetaryAmount implements Serializable {
    protected final BigDecimal value;
    protected final Currency currency;

    public MonetaryAmount(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return value + " " +currency;
    }

    public static MonetaryAmount fromString(String dbData) {
        String[] split = dbData.split(" ");
        return new MonetaryAmount(
                new BigDecimal(split[0]),
                new Currency(split[1])
        );
    }
}
