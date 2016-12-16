package com.dsr.javaschool.langtrainer.component;


import com.dsr.javaschool.langtrainer.entity.Training;
import com.dsr.javaschool.langtrainer.entity.TrainingWord;
import com.dsr.javaschool.langtrainer.entity.Word;
import com.dsr.javaschool.langtrainer.repository.TrainingRepository;
import com.dsr.javaschool.langtrainer.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class DbInitializer {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @PostConstruct
    @Transactional
    public void init() {
        createWords();
        //создаем слова для тренировок
        Word word1 = new Word("car", "машина", 1, 1);
        wordRepository.save(word1);
        Word word2 = new Word("apple", "яблоко", 2, 1);
        wordRepository.save(word2);
        Word word3 = new Word("carrot", "морковь", 1, 0);
        wordRepository.save(word3);

        Training training1 = new Training();
        training1.addTrainingWord(new TrainingWord(training1, word1));
        training1.addTrainingWord(new TrainingWord(training1, word2));
        training1.addTrainingWord(new TrainingWord(training1, word3));
        training1.setCountRight(1);
        trainingRepository.save(training1);

        Training training2 = new Training();
        training2.addTrainingWord(new TrainingWord(training2, word2));
        training2.setCountRight(1);
        trainingRepository.save(training2);
    }


    void createWords(){
        wordRepository.save(new Word("cat", "кот"));
        wordRepository.save(new Word("dog", "собака"));
        wordRepository.save(new Word("picture", "картина"));
        wordRepository.save(new Word("parrot", "попугай"));
        wordRepository.save(new Word("pen", "ручка"));
        wordRepository.save(new Word("pineapple", "ананас"));
        wordRepository.save(new Word("hair", "волосы"));
        wordRepository.save(new Word("oil", "масло"));
        wordRepository.save(new Word("soul", "душа"));
        wordRepository.save(new Word("sun", "солнце"));
        wordRepository.save(new Word("love", "любовь"));
        wordRepository.save(new Word("pain", "боль"));
        wordRepository.save(new Word("window", "окно"));
    }

}