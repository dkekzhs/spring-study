package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private final MemberRepository memberRepository;



    public Long join(Member member){
            vaildateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        });
    }
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }

}
