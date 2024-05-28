package com.sparta.calendar.dto;

import com.sparta.calendar.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private long id;
    private String title;
    private String description;
    private String author;
    private LocalDateTime createTime;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.description = schedule.getDescription();
        this.author = schedule.getAuthor();
        this.createTime = LocalDateTime.now();
    }
}
