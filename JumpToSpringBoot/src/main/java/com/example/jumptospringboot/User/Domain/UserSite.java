package com.example.jumptospringboot.User.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class UserSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Builder
    public UserSite(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
