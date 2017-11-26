package com.ilya.ivanov.slides.data.constraints.annotation;

import com.ilya.ivanov.slides.data.constraints.validator.UniqueEmail;
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
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository userRepository;

    @Override
    public void initialize(UniqueEmail uniqueUsername) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.findByEmail(email).isPresent();
    }

}
