package com.jojoldu.book.controller.posts;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jojoldu.book.domain.posts.Posts;
import com.jojoldu.book.domain.posts.PostsRepository;
import com.jojoldu.book.domain.user.User;
import com.jojoldu.book.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @AfterEach
    public void delete_desc(){
        postsRepository.deleteAll();
    }
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Post_등록() throws Exception{
        //given
        String title = "title";
        String content = "content";
        String author = "author";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder().title(title).content(content).author(author).build();

        String url = "http://localhost:" + port +  "/api/v1/posts";
        System.out.println("url = " + url);
        //when
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
//        then

        List<Posts> all = postsRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    @WithMockUser(roles = "USER")
    public void Post_수정() throws  Exception{
        //given
        Posts posts = postsRepository.save(Posts.builder().title("title").content("content").author("author").build());
        Long updateId = posts.getId();
        String expectedTitle = "동재";
        String expectedContent = "동재가 쓴 내용";

        String url = "http://localhost:" + port +  "/api/v1/posts/" +updateId;
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder().title(expectedTitle).content(expectedContent).build();

        HttpEntity<PostsUpdateRequestDto> entity = new HttpEntity<>(requestDto);
        //when
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        //then

        List<Posts> all = postsRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }


}
