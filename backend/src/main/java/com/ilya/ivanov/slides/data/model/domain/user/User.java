package com.ilya.ivanov.slides.data.model.domain.user;

import com.google.common.collect.Lists;
import com.ilya.ivanov.slides.data.model.domain.presentation.Presentation;
import com.ilya.ivanov.slides.data.model.dto.user.UserDto;
import lombok.*;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.jetbrains.annotations.Contract;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

import static com.ilya.ivanov.slides.constants.SearchConstants.namesAnalyzer;
import static com.ilya.ivanov.slides.constants.SearchConstants.usernameAnalyzer;
import static com.ilya.ivanov.slides.data.model.domain.user.User.TABLE_KEY;
import static java.util.stream.Collectors.toList;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@Entity
@Table(name = TABLE_KEY)
@Indexed
@Data
@ToString(exclude = "presentations")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class User implements UserDetails {
    public static final String TABLE_KEY = "app_user";

    public static final String ID_KEY = "id";
    public static final String USERNAME_KEY = "username";
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "password";
    public static final String FIRST_NAME_KEY = "first_name";
    public static final String LAST_NAME_KEY = "last_name";
    public static final String AUTHORITIES_TABLE_KEY = "user_authorities";
    public static final String AUTHORITIES_USER_ID_KEY = "user_id";
    public static final String AUTHORITIES_ROLE_ID_KEY = "role_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_KEY)
    private Long id;

    @Column(name = USERNAME_KEY, unique = true)
    @NonNull
    @Field
    @Analyzer(definition = usernameAnalyzer)
    private String username;

    @Column(name = EMAIL_KEY, unique = true)
    @NonNull
    private String email;

    @Column(name = PASSWORD_KEY)
    @NonNull
    private String password;

    @Column(name = FIRST_NAME_KEY)
    @NonNull
    @Field
    @Analyzer(definition = namesAnalyzer)
    private String firstName;

    @Column(name = LAST_NAME_KEY)
    @NonNull
    @Field
    @Analyzer(definition = namesAnalyzer)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = AUTHORITIES_TABLE_KEY, joinColumns
            = @JoinColumn(name = AUTHORITIES_USER_ID_KEY,
            referencedColumnName = ID_KEY),
            inverseJoinColumns = @JoinColumn(name = AUTHORITIES_ROLE_ID_KEY,
                    referencedColumnName = Presentation.ID_KEY))
    private Collection<Role> authorities;

    @OneToMany(mappedBy = Presentation.USER_FIELD, cascade = CascadeType.ALL)
    private Collection<Presentation> presentations = Lists.newArrayList();

    public void addPresentation(Presentation presentation) {
        this.presentations.add(presentation);
        if (presentation.getOwner() != this) {
            presentation.setOwner(this);
        }
    }

    @Contract(pure = true)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Contract(pure = true)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Contract(pure = true)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Contract(pure = true)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDto toDto() {
        return new UserDto(
                username,
                firstName,
                lastName,
                authorities.stream().map(Role::getAuthority).collect(toList()),
                presentations.stream().map(Presentation::getId).collect(toList())
        );
    }
}

