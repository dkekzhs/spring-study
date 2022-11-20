package com.example.jumptospringboot.Board.Answer.Service;

import com.example.jumptospringboot.Board.Answer.Domain.Answer;
import com.example.jumptospringboot.Board.Answer.Domain.AnswerRepository;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.User.Domain.UserSite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void createAnswer(Answer answer) {
        this.answerRepository.save(answer);
    }
}
