package com.dsr.javaschool.langtrainer.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "words")
public class WordController {


    @RequestMapping(value = "", method=RequestMethod.GET)
    public String index(){
        return "word/list";
    }

    @RequestMapping(value = "/translate", method=RequestMethod.GET)
    public String textTranslate(){
        return "word/translate";
    }
}
