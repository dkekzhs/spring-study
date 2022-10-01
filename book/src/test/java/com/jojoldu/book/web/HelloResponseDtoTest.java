package com.jojoldu.book.web;


import com.jojoldu.book.web.dto.HelloResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloResponseDtoTest {
    @Test
    public void 룸복_기능테스트(){
        //given
        String name = "test";
        int amount =1000;

        //when
        HelloResponseDto result = new HelloResponseDto(name, amount);

        //then
        Assertions.assertThat(result.getName()).isEqualTo(name);
        Assertions.assertThat(result.getAmount()).isEqualTo(amount);
    }
}
