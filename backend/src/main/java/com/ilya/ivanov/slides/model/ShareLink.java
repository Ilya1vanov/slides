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
public class ShareLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Getter(onMethod = @__(@JsonIgnore))
    private Long id;

    private String link;
}
