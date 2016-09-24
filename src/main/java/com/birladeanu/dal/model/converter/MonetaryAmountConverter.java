package com.birladeanu.dal.model.converter;

import com.birladeanu.dal.model.helpers.MonetaryAmount;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by pavel on 9/10/16.
 */
//For autoApply any MonetaryAmount.class
//@Converter(autoApply = true)
@Converter
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String>{
    @Override
    public String convertToDatabaseColumn(MonetaryAmount attribute) {
        return attribute.toString();
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(String dbData) {
        return MonetaryAmount.fromString(dbData);
    }
}
