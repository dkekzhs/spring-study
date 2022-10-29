package com.example.jumptospringboot.Board.Answer.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AnswerRequestDto {
    @NotEmpty(message = "내용은 필수입니다.")
    private String content;

}
