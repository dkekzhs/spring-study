package com.dongjae.skeleton_server.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MemberDto {
    int userAge;
    private String userName;
    private String userPassword;

    @Builder
    public MemberDto(String userName, int userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }

}
