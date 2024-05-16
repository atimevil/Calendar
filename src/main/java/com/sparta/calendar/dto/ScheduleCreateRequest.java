package com.sparta.calendar.dto;

import lombok.Data;

@Data
public class ScheduleCreateRequest {
    private String title;
    private String description;
    private String author;
    private String password;
    private String startDate;
}
