package com.ilya.ivanov.slides.controller;

import com.ilya.ivanov.slides.model.User;
import com.ilya.ivanov.slides.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PreAuthorize("hasAuthority('STANDARD_USER')")
public class ResourceController {
    private final GenericService userService;

    @GetMapping("/user")
    public User getUser(Principal principal) {
        return userService.findByUsername(principal.getName());
    }
}
