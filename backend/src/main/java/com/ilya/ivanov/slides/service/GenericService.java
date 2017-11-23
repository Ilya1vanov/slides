package com.ilya.ivanov.slides.service;

import com.ilya.ivanov.slides.model.User;
import com.ilya.ivanov.slides.model.RandomCity;

import java.util.List;

/**
 * Created by ilya-laptop on 06/05/17.
 */
public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

    List<RandomCity> findAllRandomCities();
}
