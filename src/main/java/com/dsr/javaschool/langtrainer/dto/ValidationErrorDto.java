package com.dsr.javaschool.langtrainer.dto;


public class ValidationErrorDto {

    private String fieldName;
    private String msg;

    public ValidationErrorDto() {
    }

    public ValidationErrorDto(String fieldName, String msg) {
        this.fieldName = fieldName;
        this.msg = msg;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
