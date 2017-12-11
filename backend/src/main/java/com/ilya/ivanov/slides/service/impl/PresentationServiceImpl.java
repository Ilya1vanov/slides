package com.ilya.ivanov.slides.service.impl;

import com.ilya.ivanov.slides.data.model.domain.presentation.Presentation;
import com.ilya.ivanov.slides.data.model.domain.presentation.SearchTag;
import com.ilya.ivanov.slides.data.model.dto.presentation.PresentationDto;
import com.ilya.ivanov.slides.data.repository.PresentationRepository;
import com.ilya.ivanov.slides.data.repository.SearchTagRepository;
import com.ilya.ivanov.slides.exception.ResourceNotFoundException;
import com.ilya.ivanov.slides.service.PresentationService;
import com.ilya.ivanov.slides.service.UserService;
import com.ilya.ivanov.slides.utils.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by i.ivanov on 11/25/17.
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PresentationServiceImpl implements PresentationService {

    private final UserService userService;

    private final SecurityUtils securityUtils;

    private final PresentationRepository presentationRepository;

    private final SearchTagRepository searchTagRepository;

    @Override
    public Collection<Presentation> getAllPresentations() {
        return presentationRepository.findAllByOwnerId(securityUtils.getUserId());
    }

    @Override
    public Presentation addPresentation(PresentationDto presentationDto) {
        val presentation = Presentation.fromDto(presentationDto);
        addTags(presentation, presentationDto);
        userService.getUser().addPresentation(presentation);
        return presentationRepository.save(presentation);
    }

    @Override
    public Presentation editPresentation(PresentationDto presentationDto) {
        val presentation = getPresentation(presentationDto);
        Presentation merge = presentation.merge(presentationDto);
        addTags(merge, presentationDto);
        return presentationRepository.save(merge);
    }

    @Override
    public void removePresentation(PresentationDto presentationDto) {
        Presentation presentation = getPresentation(presentationDto);
        userService.getUser().getPresentations()
                .removeIf(pres -> Objects.equals(pres.getId(), presentation.getId()));
        presentationRepository.delete(presentation);
    }

    @NotNull
    private Presentation getPresentation(PresentationDto presentationDto) {
        Optional<Presentation> presentation = getPresentation(presentationDto.getId());
        if (!presentation.isPresent()) {
            throw new ResourceNotFoundException("Presentation", presentationDto.getId());
        }
        return presentation.get();
    }

    private Optional<Presentation> getPresentation(Long id) {
        return userService.getUser().getPresentations().stream()
                .filter(presentation -> Objects.equals(presentation.getId(), id))
                .findAny();
    }

    private void addTags(Presentation presentation, PresentationDto presentationDto) {
        val tags = searchTagRepository.findByTagIn(presentationDto.getTags());
        val tagsFromDto = presentationDto.getTags()
                .stream().map(SearchTag::fromDto).collect(toList());
        tags.addAll(tagsFromDto);
        presentation.setSearchTags(tags);
    }
}
