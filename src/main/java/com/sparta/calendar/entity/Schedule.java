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

    public Schedule(ScheduleRequestDto request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.author = request.getAuthor();
        this.date = request.getDate();
        this.password = request.getPassword();
    }

    public void update(ScheduleRequestDto request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.author = request.getAuthor();
        this.date = request.getDate();
        this.password = request.getPassword();
    }
}
