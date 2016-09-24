/**
 * Created by pavel on 8/30/16.
 */
@org.hibernate.annotations.GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = "AUC_SEQUENCE"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "1"
                )
        })
package com.birladeanu.dal.model;