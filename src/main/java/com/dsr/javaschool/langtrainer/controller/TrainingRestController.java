package com.dsr.javaschool.langtrainer.controller;

import com.dsr.javaschool.langtrainer.entity.Training;
import com.dsr.javaschool.langtrainer.entity.Word;
import com.dsr.javaschool.langtrainer.service.TrainingService;
import com.dsr.javaschool.langtrainer.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/trainings")
public class TrainingRestController {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private WordService wordService;

    @RequestMapping(value="/get/words", method= RequestMethod.POST)
    @ResponseBody
    public List<Word> getWordsByTraining(@RequestBody Training training) {
        return wordService.getAllByTraining(training);
    }

    @RequestMapping(value="/{id}/get/words", method= RequestMethod.GET)
    @ResponseBody
    public List<Word> getWordsByIdTraining(@PathVariable Long id) {
        return wordService.getAllByIdTraining(id);
    }

    @RequestMapping(value="/{id}/get/result", method= RequestMethod.GET)
    @ResponseBody
    public List<Word> getWrongByTraining(@PathVariable Long id) {
        return wordService.getAllWrongByTraining(id);
    }

    @RequestMapping(value="/{id}/save", method= RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveWord(@PathVariable Long id, @RequestBody String russian) {
        trainingService.saveWord(id, russian);
    }

    @RequestMapping(value="/generate/text", method= RequestMethod.POST)
    @ResponseBody
    public Training generateByText(@RequestBody List<Word> words) {
        Training training = new Training();
        trainingService.addWords(training, words);
        return training;
    }

    @RequestMapping(value="/generate/{n}", method= RequestMethod.GET)
    @ResponseBody
    public Training generate(@PathVariable int n) {
        List<Word> words = wordService.getNByPriority(n);
        Training training = new Training();
        trainingService.addWords(training, words);
        return training;
    }

    @RequestMapping(value="/{id}/get/wrong", method= RequestMethod.GET)
    @ResponseBody
    public List<Word> getWrongOptions(@PathVariable Long id) {
        return wordService.getWrongOptions(id);
    }

    @RequestMapping(value="/get/answer", method=RequestMethod.POST, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String getAnswer(@RequestBody String russian) {
        return wordService.getAnswer(russian);
    }
}
