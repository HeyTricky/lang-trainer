package com.dsr.javaschool.langtrainer.validators.annotations;

import com.dsr.javaschool.langtrainer.validators.RussianValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = RussianValidator.class)
public @interface Russian {
    String message();

    Class[] groups() default {};

    Class[] payload() default {};
}
