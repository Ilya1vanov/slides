package com.ilya.ivanov.slides.data.model.dto.presentation;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel
@AllArgsConstructor
public final class SlideDto {
    private Long id;

    @NotNull
    private String content;
}
