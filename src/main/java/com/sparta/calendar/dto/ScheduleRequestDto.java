package com.sparta.calendar.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String description;
    private String title;
    private String author;
    private String password;
    private String date;
}
