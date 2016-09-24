/**
 * Created by pavel on 9/11/16.
 */
@org.hibernate.annotations.TypeDefs({
        @org.hibernate.annotations.TypeDef(
                name = "monetary_amount_chf",
                typeClass = MonetaryAmountUserType.class,
                parameters = {@Parameter(name = "convertTo", value = "CHF")}
        ),
        @org.hibernate.annotations.TypeDef(
                name = "monetary_amount_eur",
                typeClass = MonetaryAmountUserType.class,
                parameters = {@Parameter(name = "convertTo", value = "EUR")}
        )
})
package com.birladeanu.dal.model.converter;

import com.birladeanu.dal.model.helpers.MonetaryAmountUserType;
import org.hibernate.annotations.Parameter;