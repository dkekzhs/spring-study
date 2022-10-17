package com.example.jumptospringboot.Board.Question.Domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findBySubject(String subject);
    List<Question> findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);
}
