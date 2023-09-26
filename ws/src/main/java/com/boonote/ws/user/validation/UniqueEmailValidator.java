package com.boonote.ws.user.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.boonote.ws.user.User;
import com.boonote.ws.user.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    // public UniqueEmailValidator(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User inDB = userRepository.findByEmail(value);
        return inDB == null;
    }

}
