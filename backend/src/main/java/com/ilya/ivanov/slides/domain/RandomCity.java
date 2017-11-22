package com.ilya.ivanov.slides.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by ilya-laptop on 10/05/17.
 */
@Entity
@Table(name = "random_city")
@Data
public class RandomCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
