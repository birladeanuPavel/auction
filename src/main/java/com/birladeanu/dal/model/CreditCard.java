package com.birladeanu.dal.model;

import com.birladeanu.dal.model.parent.BillingDetails;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

/**
 * Created by pavel on 9/12/16.
 */
@Entity
@PrimaryKeyJoinColumn(name = "CREDITCARD_ID")
@Getter
public class CreditCard extends BillingDetails {

    @NotNull
    protected String cardNumber;
    @NotNull
    protected String expMonth;
    @NotNull
    protected String expYear;

    public CreditCard(String owner, String cardNumber, String expMonth, String expYear) {
        super(owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

}
