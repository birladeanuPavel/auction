package com.birladeanu.dal.model;

import com.birladeanu.dal.model.parent.BillingDetails;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by pavel on 9/12/16.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BankAccount extends BillingDetails {

    @NotNull
    protected String account;
    @NotNull
    protected String bankname;
    @NotNull
    protected String swift;

    public BankAccount(String owner, String account, String bankname, String swift) {
        super(owner);
        this.account = account;
        this.bankname = bankname;
        this.swift = swift;
    }

}
