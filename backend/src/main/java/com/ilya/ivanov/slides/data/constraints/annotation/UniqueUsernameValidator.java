package com.ilya.ivanov.slides.data.constraints.annotation;

import com.ilya.ivanov.slides.data.constraints.validator.UniqueUsername;
import com.ilya.ivanov.slides.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by i.ivanov on 11/26/17.
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserRepository userRepository;

    @Override
    public void initialize(UniqueUsername uniqueUsername) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.findByUsername(username).isPresent();
    }
}
