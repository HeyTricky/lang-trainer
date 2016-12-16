package com.dsr.javaschool.langtrainer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String english;
    private String russian;
    private Integer countAll;
    private Integer countRight;

    @OneToMany(mappedBy = "word")
    @JsonIgnore
    private Set<TrainingWord> trainingWords = new HashSet<TrainingWord>();

    public Word() {
        countAll = countRight = 0;
    }

    public Word(String english, String russian) {
        this.english = english;
        this.russian = russian;
        countAll = countRight = 0;
    }

    public Word(String english, String russian, Integer countAll, Integer countRight) {
        this.english = english;
        this.russian = russian;
        this.countAll = countAll;
        this.countRight = countRight;
    }

    public Integer incCountAll() {
        return countAll++;
    }

    public Integer incCountRight() {
        return countRight++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<TrainingWord> getTrainingWords() {
        return trainingWords;
    }

    public void setTrainingWords(Set<TrainingWord> trainingWords) {
        this.trainingWords = trainingWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;

        Word word = (Word) o;

        if (!id.equals(word.id)) return false;
        return true;
    }

}
