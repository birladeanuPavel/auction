package com.birladeanu.dal.model.embedabble;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by pavel on 9/24/16.
 */
@Embeddable
@Data
@AllArgsConstructor
public class Filename {

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String extension;

}
