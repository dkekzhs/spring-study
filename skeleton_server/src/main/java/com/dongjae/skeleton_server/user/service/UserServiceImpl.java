package com.dongjae.skeleton_server.user.service;

import com.dongjae.skeleton_server.common.exception.BaseException;
import com.dongjae.skeleton_server.user.domain.Member;
import com.dongjae.skeleton_server.user.dto.TokenRequest;
import com.dongjae.skeleton_server.user.dto.TokenResponse;
import com.dongjae.skeleton_server.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

import static com.dongjae.skeleton_server.common.dto.BaseResponseStatus.NOT_FOUND_USER;
import static com.dongjae.skeleton_server.common.dto.BaseResponseStatus.NOT_VERIFIED_GOOGLE_TOKEN;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final MemberRepository memberRepository;
    private final RestTemplate restTemplate;

    @Override
    public TokenResponse loginGoogle(TokenRequest tokenRequest) {
        if (!verifyGoogleToken(tokenRequest.getAccess_token())) {
            throw new BaseException(NOT_VERIFIED_GOOGLE_TOKEN);
        }
        Map<String, Object> value = fetchGoogleUserInfo(tokenRequest.getAccess_token());

        //find User ? -> token create : db insert and token create return;


        return null;
    }

    @Override
    public TokenResponse login(Map<String, String> map) {
        Optional<Member> memberByNameAndPassword = memberRepository.findMemberByNameAndPassword(map.get("userName"), map.get("userPassword"));

        return null;
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
