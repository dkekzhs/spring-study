package com.dongjae.skeleton_server.ranking;

import com.dongjae.skeleton_server.common.dto.BaseResponse;
import com.dongjae.skeleton_server.common.dto.BaseResponseStatus;
import com.dongjae.skeleton_server.member.domain.Member;
import com.dongjae.skeleton_server.ranking.dto.RankingRequest;
import com.dongjae.skeleton_server.ranking.dto.RankingResponseDto;
import com.dongjae.skeleton_server.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping
    public BaseResponse<?> updateRanking(@AuthenticationPrincipal Member member, @RequestBody RankingRequest rankingRequest){
        rankingService.updateRanking(member.toDto(), rankingRequest);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
