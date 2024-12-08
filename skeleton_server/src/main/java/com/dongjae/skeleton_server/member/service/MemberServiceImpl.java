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
import java.util.Optional;

import static com.dongjae.skeleton_server.common.dto.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RestTemplate restTemplate;

    @Override
    public MemberDto loginGoogle(TokenRequest tokenRequest) {
        Member member = null;
        MemberDto memberData;
        if (!verifyGoogleToken(tokenRequest.getAccessToken())) {
            throw new BaseException(NOT_VERIFIED_GOOGLE_TOKEN);
        }
        Map<String, Object> value = fetchGoogleUserInfo(tokenRequest.getAccessToken());

        //find User ? -> token create : db insert and token create return;
        MemberDto memberDto = valueParsing(value);
        Optional<Member> getMember = memberRepository.findByEmailAndType(memberDto.getEmail(), memberDto.getType());

        if(getMember.isEmpty()){
            member = Member.builder()
                    .name(memberDto.getName())
                    .type(memberDto.getType())
                    .connect("")
                    .name(memberDto.getEmail() + "_" + memberDto.getType())
                    .build();
            memberRepository.save(member);
        }

        if (getMember.isPresent()) {
            member = getMember.get();
        }


        return member.toDto();
    }

    private MemberDto valueParsing(Map<String, Object> value) {
        String email,type;
        try{
            email =(String) value.get("email");
            type = (String) value.get("type");
        }
        catch (Exception e){
            throw new BaseException(NOT_VALUABLE_INPUT);
        }
        return MemberDto.builder().email(email)
                .type(type)
                .build();

    }

    @Override
    public Member login(MemberDto memberDto) {
        return memberRepository.findById(0).orElseThrow(() -> new BaseException(NOT_FOUND_USER));
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
        } catch (Exception e){
            return false;
        }
    }

    private Map<String, Object> fetchGoogleUserInfo(String accessToken) throws BaseException{
        String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);
            return response.getBody();
        } catch (Exception e) {
            throw new BaseException(NOT_GET_GOOGLE_TOKEN);
        }
    }



}
