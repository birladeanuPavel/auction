package com.birladeanu.dal.model.parent;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by pavel on 9/12/16.
 */
@MappedSuperclass
@Getter
public abstract class MainModel {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;

}
