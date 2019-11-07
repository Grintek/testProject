package com.testing.controller;

import com.testing.dto.Data;
import com.testing.dto.Result;
import com.testing.domain.Question;
import com.testing.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/test")
public class TestRestController {

    private final QuestionService questionService;

    @Autowired
    public TestRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> testQuestion(){
        return questionService.getQuestions();
    }

    @PostMapping
    public Result resultTest(@RequestBody Data data){
        return questionService.resultTest(data);
    }
}
