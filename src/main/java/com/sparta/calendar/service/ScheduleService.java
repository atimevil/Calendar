package com.sparta.calendar.service;

import com.sparta.calendar.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    public SchedulesReadResponse getSchedules(String title, String description, String author, String password, String date) {
        return null;
    }

    public ScheduleReadResponse getSchedule(Long id) {
        return null;
    }

    public ScheduleCreateResponse createSchedule(ScheduleCreateRequest request) {
        return null;
    }

    public void updateSchedule(Long id, PostUpdateRequest request) {

    }

    public void deleteSchedule(Long id) {

    }
}
