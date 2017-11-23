package com.ilya.ivanov.slides.controller;

import com.ilya.ivanov.slides.model.RandomCity;
import com.ilya.ivanov.slides.model.User;
import com.ilya.ivanov.slides.service.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PreAuthorize("hasAuthority('STANDARD_USER')")
@Slf4j
public class ResourceController {
    private final GenericService userService;

    @GetMapping(value ="/cities")
    public List<RandomCity> getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        return userService.findAllRandomCities();
    }

    @GetMapping(value ="/users")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
}
