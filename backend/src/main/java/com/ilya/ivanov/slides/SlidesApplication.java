package com.ilya.ivanov.slides;

import com.ilya.ivanov.slides.domain.RandomCity;
import com.ilya.ivanov.slides.domain.Role;
import com.ilya.ivanov.slides.domain.User;
import com.ilya.ivanov.slides.repository.RandomCityRepository;
import com.ilya.ivanov.slides.repository.RoleRepository;
import com.ilya.ivanov.slides.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@Slf4j
public class SlidesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlidesApplication.class, args);
    }

    @Bean
    @Profile("dev")
    public CommandLineRunner dev(RandomCityRepository randomCityRepository, RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            populateRandomCities(randomCityRepository);
            populateUsers(roleRepository, userRepository);
        };
    }

    private void populateRandomCities(RandomCityRepository randomCityRepository) {
        val randomCities = Arrays.asList(
                new RandomCity("Bamako"),
                new RandomCity("Nonkon"),
                new RandomCity("Houston"),
                new RandomCity("Toronto"),
                new RandomCity("New York"),
                new RandomCity("Mopti"),
                new RandomCity("Koulikoro"),
                new RandomCity("Moscow"));
        randomCityRepository.save(randomCities);
    }

    private void populateUsers(RoleRepository roleRepository, UserRepository userRepository) {
        val userRole = new Role("STANDARD_USER", "Standard User - Has no admin rights");
        val adminRole = new Role("ADMIN_USER", "Admin User - Has permission to perform admin tasks");
        roleRepository.save(Arrays.asList(userRole, adminRole));

        val user = new User("john.doe", "$2a$10$teJrCEnsxNT49ZpXU7n22O27aCGbVYYe/RG6/XxdWPJbOLZubLIi2", "John", "Doe");
        user.setRoles(Collections.singletonList(userRole));
        val admin = new User("admin.admin", "$2a$10$teJrCEnsxNT49ZpXU7n22O27aCGbVYYe/RG6/XxdWPJbOLZubLIi2", "Admin", "Admin");
        admin.setRoles(Arrays.asList(userRole, adminRole));
        userRepository.save(user);
        userRepository.save(admin);
    }
}
