package com.dongjae.skeleton_server.user.domain;

import com.dongjae.skeleton_server.user.dto.MemberDto;
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
    private String name;

    @Column
    private String password;

    @Column
    private int age;

    @Builder
    public Member(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }


    public MemberDto toDto(){
        return MemberDto.builder()
                .userAge(age)
                .userName(name)
                .build();
    }
}