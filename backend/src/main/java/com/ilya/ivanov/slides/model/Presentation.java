package com.ilya.ivanov.slides.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Entity
@Table
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @Getter(onMethod = @__(@JsonIgnore))
    private User owner;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Slide> slides;

    @OneToOne(cascade = CascadeType.ALL)
    private ShareLink shareLink;
}
