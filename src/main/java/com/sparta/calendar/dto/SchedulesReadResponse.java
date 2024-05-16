//package com.sparta.calendar.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Getter
//@RequiredArgsConstructor
//public class SchedulesReadResponse {
//
//    private final long totalSchedules;
//    private final List<Schedule> schedules;
//
//    @Getter
//    @RequiredArgsConstructor
//    public static class Schedule {
//        private final Long id;
//        private final String title;
//        private final String description;
//        private final String author;
//        private final String password;
//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//        private final LocalDateTime createdTime;
//    }
//}
