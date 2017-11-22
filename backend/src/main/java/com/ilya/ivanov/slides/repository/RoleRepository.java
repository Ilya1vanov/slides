package com.ilya.ivanov.slides.repository;

import com.ilya.ivanov.slides.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ilya-laptop on 06/05/17.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
}
