package com.ilya.ivanov.slides.service;

import com.ilya.ivanov.slides.data.model.domain.user.User;

import java.util.List;

/**
 * Created by ilya-laptop on 06/05/17.
 */
public interface UserService {
    User findByUsername(String username);

    List<User> findAllUsers();

    User saveUser(User user);

    User getUser();
}
