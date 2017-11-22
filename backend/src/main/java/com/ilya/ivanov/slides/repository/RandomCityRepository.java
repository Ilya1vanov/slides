package com.ilya.ivanov.slides.repository;

import com.ilya.ivanov.slides.domain.RandomCity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ilya-laptop on 10/05/17.
 */
public interface RandomCityRepository extends CrudRepository<RandomCity, Long> {
}
