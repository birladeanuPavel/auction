package com.birladeanu.dal.model.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.PrePersist;

/**
 * Created by pavel on 10/24/16.
 */
public class UserListener {

    private static final Logger LOGGER = LogManager.getLogger();

    @PrePersist
    public void beforePersist(Object o) {
        LOGGER.debug("User pre persisted" + o);
    }

}
