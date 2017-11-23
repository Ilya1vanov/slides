package com.ilya.ivanov.slides.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ilya-laptop on 10/05/17.
 */
@Entity
@Table
@Data
@NoArgsConstructor
public final class RandomCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    public RandomCity(String name) {
        this.name = name;
    }
}
