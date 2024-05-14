package com.sparta.calendar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.calendar.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private long id;
    private String title;
    private String description;
    private String author;
    private String date;

    @JsonIgnore
    private String password;

    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }
}
