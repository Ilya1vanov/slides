package com.ilya.ivanov.slides.service.impl;

import com.ilya.ivanov.slides.data.model.domain.user.User;
import com.ilya.ivanov.slides.data.repository.UserRepository;
import com.ilya.ivanov.slides.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ilya-laptop on 07/05/17.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseGet(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser() {
        val username = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.findByUsername(username);
    }
}
