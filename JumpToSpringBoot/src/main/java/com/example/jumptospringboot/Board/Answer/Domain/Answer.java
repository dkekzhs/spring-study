package com.example.jumptospringboot.Board.Answer.Domain;

import com.example.jumptospringboot.Board.Question.Domain.Question;
import com.example.jumptospringboot.Common.BaseEntity;
import com.example.jumptospringboot.User.Domain.UserSite;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Set;


@Getter
@Entity
@NoArgsConstructor
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserSite author;

    @Column
    private String content;

    @ManyToOne
    private Question question;

    @ManyToMany
    Set<UserSite> voter;


    @Builder
    public Answer(String content, Question question, UserSite author) {
        this.content = content;
        this.question = question;
        this.author = author;
    }
    public void contentUpdate(String content){
        this.content = content;
    }
}
