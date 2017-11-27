package com.ilya.ivanov.slides.data.model.dto.search;

import com.ilya.ivanov.slides.data.model.dto.presentation.PresentationDto;
import com.ilya.ivanov.slides.data.model.dto.user.UserDto;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Created by i.ivanov on 11/27/17.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel
@AllArgsConstructor
public class SearchResults {
    private Collection<UserDto> users;

    private Collection<PresentationDto> presentations;
}
