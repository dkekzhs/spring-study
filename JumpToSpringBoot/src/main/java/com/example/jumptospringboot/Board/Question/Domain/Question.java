package com.example.jumptospringboot.Board.Question.Domain;

import com.example.jumptospringboot.Board.Answer.Domain.Answer;
import com.example.jumptospringboot.Common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;

    @Column
    private String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Answer> answerList;

    @Builder
    public Question(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public void subjectUpdate(String subject) {
        this.subject = subject;
    }

    public void addChild(List<Answer> answer) {
        this.answerList = answer;
    }
}
