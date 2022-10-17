package com.example.jumptospringboot.Board.Answer.Domain;

import com.example.jumptospringboot.Board.Question.Domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
