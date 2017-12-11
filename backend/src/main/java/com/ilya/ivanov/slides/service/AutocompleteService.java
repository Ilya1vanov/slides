package com.ilya.ivanov.slides.service;

import java.util.Collection;

/**
 * Created by i.ivanov on 12/9/17.
 */
public interface AutocompleteService {
    Collection<String> autocompleteTag(String request);
}
