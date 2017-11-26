package com.ilya.ivanov.slides;

import com.ilya.ivanov.slides.data.model.domain.presentation.Presentation;
import com.ilya.ivanov.slides.data.model.domain.user.Role;
import com.ilya.ivanov.slides.data.model.domain.user.User;
import com.ilya.ivanov.slides.data.repository.RoleRepository;
import com.ilya.ivanov.slides.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@Slf4j
public class SlidesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlidesApplication.class, args);
    }
}
