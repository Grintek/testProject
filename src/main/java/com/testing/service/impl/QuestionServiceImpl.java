package com.testing.service.impl;

import com.testing.dto.CountSuccess;
import com.testing.dto.Data;
import com.testing.dto.Result;
import com.testing.domain.Answer;
import com.testing.domain.Question;
import com.testing.reposytory.QuestionRepository;
import com.testing.service.QuestionService;
import javassist.tools.rmi.RemoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    @Override
    public List<Question> createQuestion(Question question){
        questionRepository.save(question);
        return getQuestions();
    }

    @Override
    public Question updateQuestion(Long id, Question question){
        Question upQuestion = getQuestion(id);
        upQuestion.setDescription(question.getDescription());

        boolean checked = false;
        if(!CollectionUtils.isEmpty(question.getAnswers())) {
            for (Answer answer : question.getAnswers()) {
                answer.setQuestion(upQuestion);
                if (answer.getCorrect()) {
                    checked = true;
                }
            }
            if(!checked){
             question.getAnswers().get(0).setCorrect(true);
            }
        }

        upQuestion.getAnswers().clear();
        if(question.getAnswers().size() == 1){
            question.getAnswers().get(0).setCorrect(true);
        }
        upQuestion.getAnswers().addAll(question.getAnswers());

        questionRepository.save(upQuestion);
        return upQuestion;
    }

    @Override
    public Question getQuestion(Long id){
        return questionRepository.findById(id).orElseThrow(
                () -> new RemoteException("Question not found")
        );
    }

    @Override
    public Question addAnswer(Long id, Answer answer){
        Question question = getQuestion(id);
        List<Answer> answers = question.getAnswers();

        if (answers.size() == 0) {
            answer.setCorrect(true);
        } else {
            if (answer.getCorrect()) {
                for (Answer currentAnswer : answers) {
                    currentAnswer.setCorrect(false);
                }
            }
        }

        question.addAnswer(answer);

        return questionRepository.save(question);
    }

    @Override
    public List<Question> deleteById(Long id) {
        questionRepository.deleteById(id);
        return getQuestions();
    }

    @Override
    public Result resultTest(Data data) {
        System.out.println(data.getData());
        List<Question> questions = questionRepository.findAll();
        long correct = 0;
        long incorrect = 0;
        for(CountSuccess fin : data.getData() ){

            if(fin.getAnswer() != null) {
                for (Question question : questions) {
                    assert fin.getQuestion() != null;
                    if (fin.getQuestion().equals(question.getId())) {
                        for(Answer answer : question.getAnswers()){
                            if(answer.getId().equals(fin.getAnswer())){
                                if(answer.getCorrect()){
                                    correct++;
                                }else {
                                    incorrect++;
                                }
                            }
                        }
                    }
                }
            }
        }

        return new Result(correct, incorrect);
    }

}
