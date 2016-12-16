package com.dsr.javaschool.langtrainer.validators;


import com.dsr.javaschool.langtrainer.validators.annotations.English;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnglishValidator implements ConstraintValidator<English, String> {

    @Override
    public void initialize(English english) {
        return;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[a-zA-Z]+(\\s[a-zA-Z]+)?");
    }
}
