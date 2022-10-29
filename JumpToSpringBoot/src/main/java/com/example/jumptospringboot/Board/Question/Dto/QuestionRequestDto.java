package com.example.jumptospringboot.Board.Question.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class QuestionRequestDto {
    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max=200)
    String subject;

    @NotEmpty(message = "내용은 필수항목입니다.")
    String content;

}
