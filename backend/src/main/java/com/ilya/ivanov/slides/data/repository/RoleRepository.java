package com.ilya.ivanov.slides.data.repository;

import com.ilya.ivanov.slides.data.model.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ilya-laptop on 06/05/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
