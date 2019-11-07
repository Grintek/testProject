package com.testing.dto;

import org.springframework.lang.Nullable;

public class CountSuccess {
    @Nullable
    private Long question;

    @Nullable
    private Long answer;

    public CountSuccess() {
    }

    public Long getQuestion() {
        return question;
    }

    public void setQuestion(Long question) {
        this.question = question;
    }

    public Long getAnswer() {
        return answer;
    }

    public void setAnswer(Long answer) {
        this.answer = answer;
    }
}
