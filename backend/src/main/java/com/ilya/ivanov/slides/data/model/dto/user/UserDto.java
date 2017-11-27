package com.ilya.ivanov.slides.data.model.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Created by i.ivanov on 11/25/17.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel
@AllArgsConstructor
public class UserDto {
    private String username;

    private String firstName;

    private String lastName;

    private Collection<String> authorities;

    private Collection<Long> presentations;
}
