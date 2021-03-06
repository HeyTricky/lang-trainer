package com.dsr.javaschool.langtrainer.dto;


import com.dsr.javaschool.langtrainer.service.WordService;
import com.dsr.javaschool.langtrainer.validators.annotations.English;
import com.dsr.javaschool.langtrainer.validators.annotations.Russian;
import com.dsr.javaschool.langtrainer.validators.annotations.Unique;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class CreateWordDto {

    @NotNull(message = "English word is null")
    @NotBlank(message = "English word is empty")
    @English(message = "Field 'english' should contain only english letters")
    @Unique(service = WordService.class, fieldName = "english", message = "This english word already exists!")
    private String english;

    @NotNull(message = "Russian word is null")
    @NotBlank(message = "English word is empty")
    @Russian(message = "Field 'russian' should contain only russian letters")
    @Unique(service = WordService.class, fieldName = "russian", message = "This russian word already exists!")
    private String russian;

    private Integer countAll;

    private Integer countRight;

    public CreateWordDto(){
    }

    public CreateWordDto(String english, String russian){
        this.english = english;
        this.russian = russian;
        countAll = countRight = 0;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public Integer getCountAll() {
        return countAll;
    }

    public void setCountAll(Integer countAll) {
        this.countAll = countAll;
    }

    public Integer getCountRight() {
        return countRight;
    }

    public void setCountRight(Integer countRight) {
        this.countRight = countRight;
    }
}
