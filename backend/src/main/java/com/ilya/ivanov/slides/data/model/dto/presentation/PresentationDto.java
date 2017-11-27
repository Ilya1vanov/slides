package com.ilya.ivanov.slides.data.model.dto.presentation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ApiModel
public class PresentationDto {
    private Long id;

    private String owner;

    @Size(min = 5, max = 25)
    @ApiModelProperty(required = true)
    private String title;

    @ApiModelProperty(required = true)
    private Collection<String> tags;

    private Collection<Long> slides;

    private String shareLink;
}
