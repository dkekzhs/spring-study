package com.dongjae.skeleton_server.common.util;


import com.dongjae.skeleton_server.common.exception.BaseException;
import com.dongjae.skeleton_server.member.service.MemberService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.dongjae.skeleton_server.common.dto.BaseResponseStatus.EXPIRES_ACCESS_TOKEN;
import static com.dongjae.skeleton_server.common.dto.BaseResponseStatus.OOPS;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final MemberService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String Token = getToken(request);

        try {
            // 유효한 토큰인지 확인합니다. 유효성검사
            if (Token != null && !Token.equalsIgnoreCase("null")) {
                int id = jwtUtil.validateAndGetMember(Token);
                AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userService.findById(id),
                        null,
                        AuthorityUtils.NO_AUTHORITIES
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
                ((SecurityContext) emptyContext).setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(emptyContext);
            }
            filterChain.doFilter(request, response);
        }
        catch (ExpiredJwtException e) {
            throw new BaseException(EXPIRES_ACCESS_TOKEN);
        } catch (Exception e) {
            throw new BaseException(OOPS);
        }

    }

    public String getToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }
}
