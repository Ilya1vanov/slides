package com.ilya.ivanov.slides.controller;

import com.ilya.ivanov.slides.model.User;
import com.ilya.ivanov.slides.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by i.ivanov on 11/23/17.
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PreAuthorize("hasAuthority('ADMIN_USER')")
public class AdminController {
    private final GenericService userService;

    @GetMapping(value ="/users")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
}
