package com.kouforum.backend.validations;

import org.springframework.beans.factory.annotation.Autowired;

import com.kouforum.backend.models.User;
import com.kouforum.backend.repositories.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        User inDB = userRepository.findByEmail(value);
        return inDB == null;
    }
}
