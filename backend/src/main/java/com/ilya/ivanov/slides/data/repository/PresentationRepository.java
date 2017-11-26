package com.ilya.ivanov.slides.data.repository;

import com.ilya.ivanov.slides.data.model.domain.presentation.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by i.ivanov on 11/23/17.
 */
public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    Collection<Presentation> findAllByOwnerId(Long id);
}
