package com.testing.dto;

public class Result {
    private Long correct;
    private Long incorrect;

    public Result() {
    }

    public Result(Long correct, Long incorrect) {
        this.correct = correct;
        this.incorrect = incorrect;
    }

    public Long getCorrect() {
        return correct;
    }

    public void setCorrect(Long correct) {
        this.correct = correct;
    }

    public Long getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(Long incorrect) {
        this.incorrect = incorrect;
    }
}
