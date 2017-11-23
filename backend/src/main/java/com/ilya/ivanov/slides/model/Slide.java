package com.ilya.ivanov.slides.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Entity
@Table
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Slide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id")
    @Getter(onMethod = @__(@JsonIgnore))
    private Presentation presentation;

    @Column
    private String content;
}
