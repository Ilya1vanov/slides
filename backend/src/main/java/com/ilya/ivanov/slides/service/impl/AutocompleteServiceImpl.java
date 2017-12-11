package com.ilya.ivanov.slides.service.impl;

import com.ilya.ivanov.slides.data.model.domain.presentation.SearchTag;
import com.ilya.ivanov.slides.data.repository.SearchTagRepository;
import com.ilya.ivanov.slides.service.AutocompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

import static com.ilya.ivanov.slides.constants.SearchConstants.AUTOCOMPLETE_PAGE_SIZE;
import static java.util.stream.Collectors.toList;

/**
 * Created by i.ivanov on 12/9/17.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutocompleteServiceImpl implements AutocompleteService {
    private final SearchTagRepository searchTagRepository;

    @Override
    @Transactional
    public Collection<String> autocompleteTag(String request) {
        return searchTagRepository
                .findAllByTagStartsWith(request, new PageRequest(1, AUTOCOMPLETE_PAGE_SIZE))
                .map(SearchTag::toDto).collect(toList());
    }
}
