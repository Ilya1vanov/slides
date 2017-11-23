package com.ilya.ivanov.slides.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@Entity
@Table
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String roleName;

    @Column
    @NonNull
    private String description;
}
