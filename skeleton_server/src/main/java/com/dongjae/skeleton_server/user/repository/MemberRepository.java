package com.dongjae.skeleton_server.user.repository;

import com.dongjae.skeleton_server.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Optional<Member> findMemberByNameAndPassword(String userName, String userPassword);

    Optional<Member> findById(int id);

}
