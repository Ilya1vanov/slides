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

import static java.util.stream.Collectors.toList;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@Entity
@Table(name = "app_user")
@Indexed
@Data
@ToString(exclude = "presentations")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    @NonNull
    @Field
    @Analyzer(definition = "slides-analyzer")
    private String username;

    @Column(name = "email", unique = true)
    @NonNull
    private String email;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column(name = "first_name")
    @NonNull
    @Field
    @Analyzer(definition = "slides-analyzer")
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    @Field
    @Analyzer(definition = "slides-analyzer")
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private Collection<Role> authorities;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
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

