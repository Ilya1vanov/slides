package com.ilya.ivanov.slides.data.model.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.ilya.ivanov.slides.data.model.domain.presentation.Presentation;
import lombok.*;
import org.jetbrains.annotations.Contract;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@Entity
@Table(name = "app_user")
@Data
@ToString(exclude = "presentations")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter(onMethod = @__(@JsonIgnore))
    private Long id;

    @Column(name = "username", unique = true)
    @NonNull
    private String username;

    @Column(name = "email", unique = true)
    @NonNull
    private String email;

    @Column(name = "password")
    @NonNull
    @Getter(onMethod = @__(@JsonIgnore))
    private String password;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private List<Role> authorities;

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
}

