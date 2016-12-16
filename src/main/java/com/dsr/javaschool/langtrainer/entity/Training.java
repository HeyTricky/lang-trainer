package com.dsr.javaschool.langtrainer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer countAll;
    private Integer countRight;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TrainingWord> trainingWords = new HashSet<TrainingWord>();

    public Training() {
        countAll = countRight = 0;
    }

    public void addTrainingWord(TrainingWord trainingWord) {
        trainingWords.add(trainingWord);
        countAll++;
    }

    public Integer incCountRight() {
        return countRight++;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Training)) return false;
        Training training = (Training) o;
        if (!id.equals(training.id)) return false;
        return true;

    }

}
