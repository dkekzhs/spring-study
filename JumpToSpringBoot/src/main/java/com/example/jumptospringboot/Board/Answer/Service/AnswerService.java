package com.example.jumptospringboot.Board.Answer.Service;

import com.example.jumptospringboot.Board.Answer.Domain.Answer;
import com.example.jumptospringboot.Board.Answer.Domain.AnswerRepository;
import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Common.Exception.DataNotFoundException;
import com.example.jumptospringboot.User.Domain.UserSite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer) {
        this.answerRepository.save(answer);
        return answer;
    }

    public Answer getAnswer(Long id){
        Optional<Answer> answer = answerRepository.findById(id);
        if(answer.isPresent()){
            return answer.get();
        }
        else{
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        answer.contentUpdate(content);
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    public void vote(Answer answer, UserSite userSite) {
        answer.getVoter().add(userSite);
        this.answerRepository.save(answer);
    }
}
