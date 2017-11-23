package com.ilya.ivanov.slides.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@Entity
@Table(name = "app_role")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class Role {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    @NonNull
    private String roleName;

    @Column(name = "description")
    @NonNull
    private String description;
}
