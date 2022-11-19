package com.example.jumptospringboot.User.Domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSite,Long> {

    Optional<UserSite> findByusername(String username);
}
