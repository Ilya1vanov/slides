package com.ilya.ivanov.slides.data.model.domain.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static com.ilya.ivanov.slides.data.model.domain.user.Role.TABLE_KEY;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@Entity
@Table(name = TABLE_KEY)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class Role implements GrantedAuthority {
    public static final String TABLE_KEY = "app_role";

    public static final String ID_KEY = "id";
    public static final String AUTHORITY_KEY = "authority";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_KEY)
    private Long id;

    @Column(name = AUTHORITY_KEY, unique = true)
    @NonNull
    private String authority;
}
