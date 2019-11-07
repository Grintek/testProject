package com.testing.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.testing.domain.Answer;
import com.testing.domain.Question;
import com.testing.domain.View;
import com.testing.reposytory.QuestionRepository;
import com.testing.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questions")
public class ManagerRestController {

    private final QuestionService questionService;

    @Autowired
    public ManagerRestController(QuestionService questionService, QuestionRepository qr) {
        this.questionService = questionService;
    }

    @GetMapping
    @JsonView(View.Description.class)
    public List<Question> questionList(){
        return questionService.getQuestions();
    }


    @PostMapping
    public List<Question> addQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }


    @DeleteMapping("/{id}")
    public List<Question> removeQuestion(@PathVariable("id") Long id){
        return questionService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Question question(@PathVariable("id") Long id){
        return questionService.getQuestion(id);
    }


    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable("id") Long id, @RequestBody Question question){
        return questionService.updateQuestion(id, question);
    }


    @PostMapping("/{id}/answer")
    public Question addAnswer(@PathVariable("id")Long id, @RequestBody Answer answer){
       return questionService.addAnswer(id, answer);
    }

}
