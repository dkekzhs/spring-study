package com.jojoldu.book.domain.posts;


import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired PostsRepository pr;

    @AfterEach
    public void cleanUp(){

    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "게시글";
        String comment = "zz";
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        pr.save(Posts.builder().title(title).content(comment).author("동재").build());


        //when
        List<Posts> postsList = pr.findAll();
        Posts posts = postsList.get(0);
        //then
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(comment);
        Assertions.assertThat(posts.getLocalDateTime()).isAfter(now);
        Assertions.assertThat(posts.getLastLocalDateTime()).isAfter(now);

    }
}
