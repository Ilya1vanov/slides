package com.ilya.ivanov.slides.repository;

import com.ilya.ivanov.slides.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ilya-laptop on 06/05/17.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
