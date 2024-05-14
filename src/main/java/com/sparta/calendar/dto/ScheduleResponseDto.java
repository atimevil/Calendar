package com.sparta.calendar.dto;

import com.sparta.calendar.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private long id;
    private String title;
    private String description;
    private String author;
    private String password;
    private String date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.description = schedule.getDescription();
        this.author = schedule.getAuthor();
        this.password = schedule.getPassword();
        this.date = schedule.getDate();
    }
}
