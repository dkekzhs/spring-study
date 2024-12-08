package com.dongjae.skeleton_server.ranking.service;

import com.dongjae.skeleton_server.member.dto.MemberDto;
import com.dongjae.skeleton_server.ranking.domain.Ranking;
import com.dongjae.skeleton_server.ranking.dto.RankingRequest;
import com.dongjae.skeleton_server.ranking.dto.RankingResponseDto;
import com.dongjae.skeleton_server.ranking.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl  implements RankingService{
    private final RankingRepository rankingRepository;
    private final int LIMIT = 10;
    @Override
    public List<RankingResponseDto> getRanking(int page) {
        List<Object[]> rankingsRaw = rankingRepository.findTopRankings(LIMIT, (page+1)* LIMIT);
        List<RankingResponseDto> rankings = new ArrayList<>();

        for (Object[] row : rankingsRaw) {
            RankingResponseDto dto = new RankingResponseDto();
            dto.setUserName((String) row[0]);
            dto.setScore((Integer) row[1]);
            dto.setRank(((Number) row[2]).intValue());
            rankings.add(dto);
        }

        return rankings;
    }

    @Override
    public void updateRanking(MemberDto dto, RankingRequest rankingRequest) {
        Ranking ranking = null;
        Optional<Ranking> Oranking = rankingRepository.findRankingByUserId(Long.parseLong(dto.getId()));

        if(Oranking.isPresent()){
            ranking = Oranking.get();
            if(ranking.getScore() < rankingRequest.getScore()){
                ranking.updateScore(rankingRequest.getScore());
            }
        }
        else{
            ranking = Ranking.builder()
                    .userId(Long.parseLong(dto.getId()))
                    .score(rankingRequest.getScore())
                    .build();

        }
        rankingRepository.save(ranking);

    }
}
