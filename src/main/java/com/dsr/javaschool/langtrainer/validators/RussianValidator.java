package com.dsr.javaschool.langtrainer.validators;


import com.dsr.javaschool.langtrainer.validators.annotations.Russian;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RussianValidator implements ConstraintValidator<Russian, String> {

    @Override
    public void initialize(Russian russian) {
        return;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[а-яА-Я]+");
    }
}
