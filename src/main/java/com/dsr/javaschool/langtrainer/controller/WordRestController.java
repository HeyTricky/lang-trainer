package com.dsr.javaschool.langtrainer.controller;


import com.dsr.javaschool.langtrainer.dto.CreateWordDto;
import com.dsr.javaschool.langtrainer.dto.UpdateWordDto;
import com.dsr.javaschool.langtrainer.dto.ValidListDto;
import com.dsr.javaschool.langtrainer.entity.Word;
import com.dsr.javaschool.langtrainer.exception.ValidationException;
import com.dsr.javaschool.langtrainer.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("rest/words")
public class WordRestController {

    @Autowired
    private WordService wordService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Word> getAll() {
        return wordService.getAll();
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    @ResponseBody
    public Word create(@RequestBody @Valid CreateWordDto word, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ValidationException("Validation error!", bindingResult);
        return wordService.create(word);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Word update(@PathVariable Long id, @RequestBody @Valid UpdateWordDto word, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ValidationException("Validation error!", bindingResult);
        return wordService.update(id, word);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Word delete(@PathVariable Long id) {
        return wordService.delete(id);
    }

    @RequestMapping(value = "/translate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object translateText(@RequestBody String text) throws IOException {
        return wordService.translateText(text);
    }

    @RequestMapping(value = "/add/all", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public void addAll(@RequestBody @Valid ValidListDto<CreateWordDto> words, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors())
            throw new ValidationException("Validation error!", bindingResult);
        wordService.addAll(words);
    }

}
