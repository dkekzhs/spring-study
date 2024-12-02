package com.dongjae.skeleton_server.member.service;

import com.dongjae.skeleton_server.common.exception.BaseException;
import com.dongjae.skeleton_server.member.domain.Member;
import com.dongjae.skeleton_server.member.dto.MemberDto;
import com.dongjae.skeleton_server.member.dto.TokenRequest;
import com.dongjae.skeleton_server.member.dto.TokenResponse;
import com.dongjae.skeleton_server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.dongjae.skeleton_server.common.dto.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RestTemplate restTemplate;

    @Override
    public TokenResponse loginGoogle(TokenRequest tokenRequest) {
        if (!verifyGoogleToken(tokenRequest.getAccessToken())) {
            throw new BaseException(NOT_VERIFIED_GOOGLE_TOKEN);
        }
        Map<String, Object> value = fetchGoogleUserInfo(tokenRequest.getAccessToken());

        //find User ? -> token create : db insert and token create return;


        return null;
    }

    @Override
    public Member login(MemberDto memberDto) {
        System.out.println(memberDto.getUserName());
        System.out.println(memberDto.getUserPassword());
        return memberRepository.findById(memberDto.getUserAge()).orElseThrow(() -> new BaseException(NOT_FOUND_USER));
    }

    @Override
    public Member findById(int id) throws BaseException {
        return memberRepository.findById(id).orElseThrow(() -> new BaseException(NOT_FOUND_USER));
    }

    private boolean verifyGoogleToken(String accessToken) {
        String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?access_token=" + accessToken;

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(tokenInfoUrl, Map.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }

    private Map<String, Object> fetchGoogleUserInfo(String accessToken) {
        String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }



}
