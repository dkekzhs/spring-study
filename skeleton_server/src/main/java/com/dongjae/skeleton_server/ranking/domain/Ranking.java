package com.dongjae.skeleton_server.ranking.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long userId;

    @Column
    private int score;


    public void updateScore(int score){
        this.score = score;
    }

    @Builder
    public Ranking(long userId, int score) {
        this.userId = userId;
        this.score = score;
    }
}
