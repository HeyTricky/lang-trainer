package com.dsr.javaschool.langtrainer.exception;


import org.springframework.validation.BindingResult;

public class ValidationException extends RuntimeException {

    private BindingResult bindingResult;

    public ValidationException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
