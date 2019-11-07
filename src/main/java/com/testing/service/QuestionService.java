package com.testing.service;

import com.testing.dto.Data;
import com.testing.dto.Result;
import com.testing.domain.Answer;
import com.testing.domain.Question;

import java.util.List;


public interface QuestionService {

    List<Question> getQuestions();

    List<Question> createQuestion(Question question);

    Question updateQuestion(Long id, Question question);

    Question getQuestion(Long id);

    Question addAnswer(Long id, Answer answer);

    List<Question> deleteById(Long id);

    Result resultTest(Data data);
}
