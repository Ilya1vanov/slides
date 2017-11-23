package com.ilya.ivanov.slides.service.impl;

import com.ilya.ivanov.slides.model.RandomCity;
import com.ilya.ivanov.slides.model.User;
import com.ilya.ivanov.slides.repository.RandomCityRepository;
import com.ilya.ivanov.slides.repository.UserRepository;
import com.ilya.ivanov.slides.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ilya-laptop on 07/05/17.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenericServiceImpl implements GenericService {
    private final UserRepository userRepository;

    private final RandomCityRepository randomCityRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public List<RandomCity> findAllRandomCities() {
        return (List<RandomCity>)randomCityRepository.findAll();
    }
}
