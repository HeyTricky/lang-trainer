package com.dsr.javaschool.langtrainer.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "", method= RequestMethod.GET)
    public String index(){
        return "main/index";
    }
}
