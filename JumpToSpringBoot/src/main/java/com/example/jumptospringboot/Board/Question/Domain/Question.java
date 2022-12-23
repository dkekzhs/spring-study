package com.example.jumptospringboot.Board.Question.Domain;

import com.example.jumptospringboot.Board.Answer.Domain.Answer;
import com.example.jumptospringboot.Common.BaseEntity;
import com.example.jumptospringboot.User.Domain.UserSite;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserSite author;
    @Column(length = 200)
    private String subject;

    @Column
    private String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Answer> answerList;

    @ManyToMany
    private Set<UserSite> voter;

    @Builder
    public Question(String subject, String content, UserSite author) {
        this.subject = subject;
        this.content = content;
        this.author = author;
    }

    public void subjectUpdate(String subject) {
        this.subject = subject;
    }

    public void addChild(List<Answer> answer) {
        this.answerList = answer;
    }
    public void modify(String subject , String content){
        this.subject = subject;
        this.content = content;
    }


}
