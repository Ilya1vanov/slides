package com.ilya.ivanov.slides.model;

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
public final class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String authority;

    @Column
    @NonNull
    private String description;
}
