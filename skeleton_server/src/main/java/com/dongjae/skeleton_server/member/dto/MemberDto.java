package com.dongjae.skeleton_server.member.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDto {

    private String email;
    private String type;
    private String connect;
    private String name;
    private String id;

    @Builder
    public MemberDto(String email, String type, String connect,String name,String id) {
        this.email = email;
        this.type = type;
        this.connect = connect;
        this.name = name;
        this.id = id;
    }
}
