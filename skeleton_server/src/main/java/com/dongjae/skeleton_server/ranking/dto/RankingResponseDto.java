package com.dongjae.skeleton_server.ranking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingResponseDto {
    private String userName;
    private int score;
    private int rank;
}
