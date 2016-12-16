package com.dsr.javaschool.langtrainer.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class TrainingWord implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @Id
    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;
    private Boolean isRight;

    public TrainingWord(Training training, Word word) {
        this.training = training;
        this.word = word;
        isRight = false;
    }

    public TrainingWord(){
        isRight=false;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainingWord)) return false;

        TrainingWord that = (TrainingWord) o;

        if (!training.equals(that.training)) return false;
        if (!word.equals(that.word)) return false;
        return isRight != null ? isRight.equals(that.isRight) : that.isRight == null;
    }

}
