package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    public MemberService(MemoryMemberRepository memoryMemberRepository) {
        this.memoryMemberRepository = memoryMemberRepository;
    }

    private final MemoryMemberRepository memoryMemberRepository;

    public Long join(Member member){
        vaildateDuplicateMember(member);
        memoryMemberRepository.save(member);
        return member.getId();
    }

    private void vaildateDuplicateMember(Member member) {
        memoryMemberRepository.findByName(member.getName())
        .ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        });
    }
    public List<Member> findMembers(){
        return memoryMemberRepository.findall();
    }

    public Optional<Member> findOne(Long id){
        return memoryMemberRepository.findById(id);
    }

}
