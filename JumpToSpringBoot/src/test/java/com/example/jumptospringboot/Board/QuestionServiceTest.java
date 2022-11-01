package com.example.jumptospringboot.Board;

import com.example.jumptospringboot.Board.Answer.Service.AnswerService;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Board.Question.Service.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @AfterEach
    public void 초기회(){
        questionService.deleteAll();
    }
    @Test
    public void 질문생성서비스(){
        questionService.createQuestion(Question.builder().subject("제목생성").content("문제생성").build());
    }
    @Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            Question build = Question.builder().subject(subject).content(content).build();
            this.questionService.createQuestion(build);
        }
}

}
