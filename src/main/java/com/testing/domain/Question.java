package com.testing.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.ID.class)
    private Long id;
    @NotNull
    @JsonView(View.Description.class)
    private String description;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView({View.Answer.class})
    private List<Answer> answers;

    public Question() {
        this.answers = new ArrayList<>();
    }

    public Question(String description) {
        this();
        this.description = description;
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer){
        answer.setQuestion(null);
        this.answers.remove(answer);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
