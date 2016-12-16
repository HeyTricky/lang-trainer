package com.dsr.javaschool.langtrainer.dto;


public class ParsedWordDto {

    private String english;

    private String russian;

    private Boolean isExists;



    public ParsedWordDto(){
        isExists=false;
    }

    public ParsedWordDto(String english, String russian, Boolean isExists){
        this.english = english;
        this.russian = russian;
        this.isExists=isExists;
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

    public Boolean getExists() {
        return isExists;
    }

    public void setExists(Boolean exists) {
        isExists = exists;
    }
}
