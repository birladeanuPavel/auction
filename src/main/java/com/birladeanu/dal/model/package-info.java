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
@org.hibernate.annotations.FetchProfiles({
        @FetchProfile(
                name = Item.PROFILE_JOIN_ITEM_IMAGE,
                fetchOverrides = @FetchProfile.FetchOverride(
                        association = "itemImages",
                        entity = Item.class,
                        mode = FetchMode.JOIN
                )
        ),
        @FetchProfile(
                name = Item.PROFILE_JOIN_BIDS,
                fetchOverrides = @FetchProfile.FetchOverride(
                        association = "bids",
                        entity = Item.class,
                        mode = FetchMode.JOIN
                )
        )
})
package com.birladeanu.dal.model;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;