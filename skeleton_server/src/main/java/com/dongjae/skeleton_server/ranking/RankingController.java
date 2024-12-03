package com.dongjae.skeleton_server.ranking;

import com.dongjae.skeleton_server.common.dto.BaseResponse;
import com.dongjae.skeleton_server.common.dto.BaseResponseStatus;
import com.dongjae.skeleton_server.ranking.dto.RankingResponseDto;
import com.dongjae.skeleton_server.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ranking")
public class RankingController {
    private final RankingService rankingService;

    @GetMapping("/get")
    public BaseResponse<?> getRankingTopTen(@RequestBody @RequestParam(value = "page", defaultValue = "0") int page){
        List<RankingResponseDto> ranking = rankingService.getRanking(page);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS, ranking);
    }
}
