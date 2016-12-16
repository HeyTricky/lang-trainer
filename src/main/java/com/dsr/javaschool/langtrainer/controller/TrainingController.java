package com.dsr.javaschool.langtrainer.controller;

import com.dsr.javaschool.langtrainer.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @RequestMapping(value = "/statistics", method= RequestMethod.GET)
    public String statistics(ModelMap modelMap){
        modelMap.addAttribute("trainings", trainingService.getAll());
        return "/training/list";
    }

    @RequestMapping(value = "/new", method= RequestMethod.GET)
    public String create(){
        return "/training/new";
    }

    @RequestMapping(value = "/{id}/new/text", method= RequestMethod.GET)
    public String createFromText(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("idTraining", id);
        return "/training/text";
    }
}
