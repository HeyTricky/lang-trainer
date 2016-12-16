package com.dsr.javaschool.langtrainer.service;

import com.dsr.javaschool.langtrainer.entity.Training;
import com.dsr.javaschool.langtrainer.entity.TrainingWord;
import com.dsr.javaschool.langtrainer.entity.Word;
import com.dsr.javaschool.langtrainer.repository.TrainingRepository;
import com.dsr.javaschool.langtrainer.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private WordRepository wordRepository;

    @Transactional
    public List<Training> getAll() {
        return trainingRepository.findAllByOrderByIdDesc();
    }

    @Transactional
    public Training getById(Long id) {
        return trainingRepository.findById(id);
    }

    @Transactional
    public void addWords(Training training, List<Word> words) {
        for (Word word :
                words) {
            Word wordFromDB = wordRepository.findByEnglish(word.getEnglish());
            wordFromDB.incCountAll();
            wordRepository.save(wordFromDB);
            TrainingWord trainingWord = new TrainingWord(training, wordFromDB);
            training.addTrainingWord(trainingWord);
        }
        trainingRepository.save(training);
    }

    @Transactional
    public void saveWord(Long id, String russian) {
        Training training = trainingRepository.findById(id);
        training.incCountRight();
        Word word = wordRepository.findByRussian(russian);
        word.incCountRight();
        Set<TrainingWord> trainingWords = training.getTrainingWords();
        for (TrainingWord trainingWord : trainingWords) {
            if (Objects.equals(trainingWord.getWord(), word)) {
                trainingWord.setRight(true);
                break;
            }
        }
        wordRepository.save(word);
        trainingRepository.save(training);
    }
}
