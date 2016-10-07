package com.birladeanu.service.impl;

import com.birladeanu.dal.model.User;
import com.birladeanu.dal.repository.UserRepository;
import com.birladeanu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pavel on 10/7/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
