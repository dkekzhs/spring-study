package com.dongjae.skeleton_server.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private String secret = "194160a103f8b16f4e0ef95d32e9400f45a02da31f364fb4d35703b185fe9aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa63";
    private SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

    private long accessTokenValidity = 1000 * 60 * 15;  // 15분
    private long refreshTokenValidity = 1000 * 60 * 60 * 24 * 7;  // 7일

    public String generateAccessToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, accessTokenValidity);
    }

    public String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, refreshTokenValidity);
    }

    private String createToken(Map<String, Object> claims, String subject, long expirationTime) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public int validateAndGetMember(String token) {
        Claims claims = extractAllClaims(token);
        return Integer.parseInt(claims.getSubject());
    }

    // 토큰 유효성 검증
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractAllClaims(token).getSubject();
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    // 토큰에서 모든 클레임 추출
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)  // 서명 검증 키 설정
                .build()
                .parseClaimsJws(token)
                .getBody();  // 전체 클레임 반환
    }

    // 토큰 만료 확인
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
