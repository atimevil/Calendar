package com.sparta.calendar.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyResponseDto {
    private Long id;
    private String content;
    private LocalDateTime replyDate;
    private String author;

    public ReplyResponseDto(Long id, String content, String username) {
        this.id = id;
        this.content = content;
        this.replyDate = LocalDateTime.now();
        this.author = username;
    }
}
