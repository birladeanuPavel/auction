package com.birladeanu.dal.model.converter;

import com.birladeanu.dal.model.helpers.GermanZipcode;
import com.birladeanu.dal.model.helpers.SwissZipcode;
import com.birladeanu.dal.model.helpers.Zipcode;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;

/**
 * Created by pavel on 9/10/16.
 */
@Converter(autoApply = true)
public class ZipcodeConverter implements AttributeConverter<Zipcode, String> {
    @Override
    public String convertToDatabaseColumn(Zipcode attribute) {
        return attribute.toString();
    }

    @Override
    public Zipcode convertToEntityAttribute(String dbData) {
        if (dbData.length() == 5)
            return new GermanZipcode(dbData);
        else if (dbData.length() == 4)
            return new SwissZipcode(dbData);
        throw new IllegalArgumentException(
                "Unsupported zipcode in database: " + dbData
        );
    }
}
