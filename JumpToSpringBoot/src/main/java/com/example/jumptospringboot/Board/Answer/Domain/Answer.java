package com.example.jumptospringboot.Board.Answer.Domain;

import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.transaction.Transactional;


@Getter
@Entity
@NoArgsConstructor
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @ManyToOne
    private Question question;

    @Builder
    public Answer(String content, Question question) {
        this.content = content;
        this.question = question;
    }
}
