package com.dongjae.skeleton_server.member.domain;

import com.dongjae.skeleton_server.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    private String type;

    @Column
    private String connect;
    @Column
    private String name;

    @Builder
    public Member(String email, String type, String connect, String name) {

        this.email = email;
        this.type = type;
        this.connect = connect;
        this.name = name;
    }

    public MemberDto toDto(){
        return MemberDto.builder()
                .email(email)
                .type(type)
                .name(name)
                .build();
    }
}