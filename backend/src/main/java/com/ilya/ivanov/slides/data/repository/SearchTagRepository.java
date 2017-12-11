package com.ilya.ivanov.slides.data.repository;

import com.ilya.ivanov.slides.data.model.domain.presentation.SearchTag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by i.ivanov on 12/9/17.
 */
public interface SearchTagRepository extends JpaRepository<SearchTag, Long> {
    Set<SearchTag> findByTagIn(Set<String> tags);

    Stream<SearchTag> findAllByTagStartsWith(String tag, Pageable pageable);
}
