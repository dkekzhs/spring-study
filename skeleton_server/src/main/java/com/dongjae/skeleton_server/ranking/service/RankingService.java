package com.dongjae.skeleton_server.ranking.service;

import com.dongjae.skeleton_server.member.dto.MemberDto;
import com.dongjae.skeleton_server.ranking.dto.RankingRequest;
import com.dongjae.skeleton_server.ranking.dto.RankingResponseDto;

import java.util.List;

public interface RankingService {

    List<RankingResponseDto> getRanking(int page);


    void updateRanking(MemberDto dto, RankingRequest rankingRequest);
}
