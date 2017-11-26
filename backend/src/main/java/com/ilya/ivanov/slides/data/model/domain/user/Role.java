package com.ilya.ivanov.slides.data.model.domain.user;

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
public final class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "authority", unique = true)
    @NonNull
    private String authority;

    @Column(name = "description")
    @NonNull
    private String description;
}
