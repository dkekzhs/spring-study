package com.example.jumptospringboot.Board;


import com.example.jumptospringboot.Board.Answer.Domain.Answer;
import com.example.jumptospringboot.Board.Answer.Domain.AnswerRepository;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Board.Question.Domain.QuestionRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QuestionTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    final String subject = "한글테스트";
    final String content = "content";
    final String comment = "댓글";
    final String comment1 = "댓글2";


    @BeforeEach
    public void setUp(){
        Question question = Question.builder().subject(subject).content(content).build();
        Question question1 = Question.builder().subject(subject).content(content).build();
        List<Question> questionList = List.of(question, question1);
        this.questionRepository.saveAllAndFlush(questionList);

        Answer answer = Answer.builder().content(comment).question(question).build();
        Answer answer2 = Answer.builder().content(comment1).question(question).build();
        List<Answer> answerList = List.of(answer, answer2);
        question.addChild(answerList);
        this.answerRepository.saveAllAndFlush(answerList);

    }

    @AfterEach
    public void 데이터초기화(){
        this.questionRepository.deleteAll();
    }

    @Test
    public void 게시글_전체(){
        List<Question> all = questionRepository.findAll();
        Assertions.assertEquals(2,all.size());
        System.out.println("all.size() = " + all.size());
    }

    @Test
    public void 게시글_찾기(){
        Optional<Question> id = questionRepository.findById(1L);
        if(id.isPresent()){
            String content = id.get().getContent();
            System.out.println("content = " + content);
        }
    }
    @Test
    public void 게시글_제목_찾기(){
        List<Question> bySubject = questionRepository.findBySubject(subject);
        Assertions.assertEquals(subject,bySubject.get(0).getSubject());
    }
    @Test
    public void 게시글_제목_내용_찾기(){
        List<Question> SAC = questionRepository.findBySubjectAndContent(subject, content);
        Assertions.assertEquals("content",SAC.get(0).getContent());

    }

    @Test
    public void 게시글_라이크_찾기(){
        List<Question> bySubjectLike = questionRepository.findBySubjectLike("한글%");
        Question question = bySubjectLike.get(0);
        Assertions.assertEquals(subject,question.getSubject());
    }
    @Test
    public void 게시글_수정(){
        List<Question> question = questionRepository.findAll();
        Question question1 = question.get(0);
        question1.subjectUpdate("한글변경");
        this.questionRepository.save(question1);
    }
    @Test
    public void 댓글_검색(){
        //when
        List<Answer> all = this.answerRepository.findAll();

        //then
        Assertions.assertEquals(2,all.size());
        for (Answer data: all ) {
            System.out.println("data = " + data.getContent());

        }
    }





}
