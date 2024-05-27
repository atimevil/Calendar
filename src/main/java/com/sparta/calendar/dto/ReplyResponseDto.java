package com.sparta.calendar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyResponseDto {
    private Long id;
    private String content;

    public ReplyResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
