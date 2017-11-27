package com.ilya.ivanov.slides.service;

import com.ilya.ivanov.slides.data.model.dto.search.SearchResults;

/**
 * Created by i.ivanov on 11/27/17.
 */
public interface SearchService {
    SearchResults fullTextSearch(String request);
}
