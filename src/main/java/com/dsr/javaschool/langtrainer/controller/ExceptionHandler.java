package com.dsr.javaschool.langtrainer.controller;

import com.dsr.javaschool.langtrainer.dto.ValidationErrorDto;
import com.dsr.javaschool.langtrainer.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, ModelMap modelMap) {
        modelMap.addAttribute("error", e.getMessage());
        return "error";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationErrorDto> validationErrorHandler(ValidationException e) {
        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map((x) -> new ValidationErrorDto(x.getField(), x.getDefaultMessage()))
                .collect(toList());

    }
}
