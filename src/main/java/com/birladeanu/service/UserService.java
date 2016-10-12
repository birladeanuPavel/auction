package com.birladeanu.service;

import com.birladeanu.dal.model.User;

/**
 * Created by pavel on 10/7/16.
 */
public interface UserService {

    void save(User user);

    User findOne(Long id);

}
