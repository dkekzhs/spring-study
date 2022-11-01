package com.example.jumptospringboot;

import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Board.Question.Service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JumpToSpringBootApplicationTests {
    @Autowired
    QuestionService questionService;
    @Test
    void contextLoads() {
    }



}
