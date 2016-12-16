package com.dsr.javaschool.langtrainer.service;

import com.dsr.javaschool.langtrainer.dto.CreateWordDto;
import com.dsr.javaschool.langtrainer.dto.ParsedWordDto;
import com.dsr.javaschool.langtrainer.dto.UpdateWordDto;
import com.dsr.javaschool.langtrainer.entity.Training;
import com.dsr.javaschool.langtrainer.entity.Word;
import com.dsr.javaschool.langtrainer.repository.TrainingRepository;
import com.dsr.javaschool.langtrainer.repository.WordRepository;
import com.dsr.javaschool.langtrainer.utils.TextParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WordService implements FieldValueExists {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TranslateService translateService;


    @Transactional
    public List<Word> getAll() {
        return wordRepository.findAllByOrderByEnglishAsc();
    }

    @Transactional
    public String getAnswer(String en) {
        return wordRepository.findByEnglish(en).getRussian();
    }

    @Transactional
    public List<Word> getAllByTraining(Training training) {
        return wordRepository.findAllByTraining(training);
    }

    @Transactional
    public List<Word> getAllByIdTraining(Long id) {
        Training training = trainingRepository.findById(id);
        return wordRepository.findAllByTraining(training);
    }

    @Transactional
    public List<Word> getAllWrongByTraining(Long id) {
        Training training = trainingRepository.findById(id);
        List<Word> words = wordRepository.findAllWrongByTraining(training);
        return words;

    }

    @Transactional
    public List<Word> getWrongOptions(Long id) {
        return wordRepository.findWrongOptions(id);
    }

    @Transactional
    public List<Word> getAllByPriority() {
        return wordRepository.findAllByPriority();
    }

    @Transactional
    public List<Word> getNByPriority(int n) {
        return wordRepository.findNByPriority(n);
    }

    @Transactional
    public Word create(CreateWordDto createWordDto) {
        Word word = new Word(createWordDto.getEnglish(), createWordDto.getRussian());
        return wordRepository.save(word);
    }

    @Transactional
    public Word save(Word word) {
        return wordRepository.save(word);
    }

    @Transactional
    public Word update(Long id, UpdateWordDto newWord) {
        Word word = wordRepository.findOne(id);
        word.setEnglish(newWord.getEnglish());
        word.setRussian(newWord.getRussian());
        return wordRepository.save(word);
    }

    @Transactional
    public Word delete(Long id) {
        Word word = wordRepository.findOne(id);
        wordRepository.delete(id);
        return word;
    }

    @Transactional
    public void addAll(List<CreateWordDto> words) {
        words.forEach(this::create);
    }

    @Transactional
    public List<ParsedWordDto> translateText(String text) throws IOException {
        List<String> parsedText = TextParser.parse(text);
        List<ParsedWordDto> words = new ArrayList<>();
        List<String> englishWords = wordRepository.findAllEnglishWord();
        List<String> russianWords = wordRepository.findAllRussianWord();
        for (String elem : parsedText) {
            if (elem.matches("[a-zA-Z]+(\\s[a-zA-Z]+)?"))
                words.add(new ParsedWordDto(elem, translateService.translateEnglishWord(elem), englishWords.contains(elem)));

            else if (elem.matches("[а-яА-Я]+"))
                words.add(new ParsedWordDto(translateService.translateRussianWord(elem), elem, russianWords.contains(elem)));
        }
        return words;
    }

    @Override

    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        if (!(fieldName.equals("english") || fieldName.equals("russian"))) {
            throw new UnsupportedOperationException("Field name not supported");
        }
        if (value == null) {
            return false;
        }
        if (((String) value).matches("[a-zA-Z]+(\\s[a-zA-Z]+)?"))
            return wordRepository.existsByEnglish(value.toString());
        else if (((String) value).matches("[а-яА-Я]+"))
            return wordRepository.existsByRussian(value.toString());
        return false;
    }
}
