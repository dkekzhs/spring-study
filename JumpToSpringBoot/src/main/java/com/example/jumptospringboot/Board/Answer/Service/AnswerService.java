package com.example.jumptospringboot.Board.Answer.Service;

import com.example.jumptospringboot.Board.Answer.Domain.Answer;
import com.example.jumptospringboot.Board.Answer.Domain.AnswerRepository;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void createAnswer(Question question,String content) {
        Answer build = Answer.builder().question(question).content(content).build();
        this.answerRepository.save(build);
    }
}
