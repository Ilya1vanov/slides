package com.ilya.ivanov.slides.service;

import com.ilya.ivanov.slides.data.model.domain.presentation.Presentation;
import com.ilya.ivanov.slides.data.model.dto.presentation.PresentationDto;

import java.util.Collection;

/**
 * Created by i.ivanov on 11/25/17.
 */
public interface PresentationService {

    Collection<Presentation> getAllPresentations();

    Presentation addPresentation(PresentationDto presentationDto);

    Presentation editPresentation(PresentationDto presentationDto);

    void removePresentation(PresentationDto presentationDto);
}
