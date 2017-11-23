package com.ilya.ivanov.slides.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Created by ilya-laptop on 10/05/17.
 */
@Entity
@Table(name = "random_city")
@Data
@NoArgsConstructor
public final class RandomCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public RandomCity(String name) {
        this.name = name;
    }
}
