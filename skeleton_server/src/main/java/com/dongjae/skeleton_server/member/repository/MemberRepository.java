package com.dongjae.skeleton_server.member.repository;

import com.dongjae.skeleton_server.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {

    Optional<Member> findById(int id);

}
