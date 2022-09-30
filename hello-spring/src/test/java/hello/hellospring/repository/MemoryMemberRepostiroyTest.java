package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryMemberRepostiroyTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);

    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName(member1.getName()).get();

        Assertions.assertThat(member1).isEqualTo(result);
        Assertions.assertThat(member1).isEqualTo(result);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> all = repository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
    }
}   
