package com.dongjae.skeleton_server.ranking.service;

import com.dongjae.skeleton_server.ranking.dto.RankingResponseDto;
import com.dongjae.skeleton_server.ranking.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl  implements RankingService{
    private final RankingRepository rankingRepository;
    private final int OFFSET = 10;
    @Override
    public List<RankingResponseDto> getRanking(int page) {
        List<Object[]> rankingsRaw = rankingRepository.findTopRankings(10, (page+1)* 10 - OFFSET);
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
}
