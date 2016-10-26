package com.birladeanu.dal.model;

import com.birladeanu.dal.model.parent.MainModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;

/**
 * Created by pavel on 10/24/16.
 */
@Entity
@Immutable
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class ItemAudit extends MainModel{

    protected Long itemId;

    protected String description;

}
