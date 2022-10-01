package com.jojoldu.book.web.dto;

import com.jojoldu.book.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime lastLocalDateTime;

    public PostListResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.author = posts.getAuthor();
        this.lastLocalDateTime = posts.getLastLocalDateTime();
    }
}
