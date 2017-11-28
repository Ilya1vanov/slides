package com.ilya.ivanov.slides.data.model.dto.validation;

import com.ilya.ivanov.slides.data.constraints.validator.UniqueEmail;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by i.ivanov on 11/26/17.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel
public final class EmailDto {
    @UniqueEmail
    private String email;
}
