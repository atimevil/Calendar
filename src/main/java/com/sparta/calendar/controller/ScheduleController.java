package com.sparta.calendar.controller;

import com.sparta.calendar.dto.ScheduleRequestDto;
import com.sparta.calendar.dto.ScheduleResponseDto;
import com.sparta.calendar.entity.Schedule;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final Map<Long, Schedule> schedules = new HashMap<>();

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);

        long maxId = schedules.size() > 0 ? Collections.max(schedules.keySet()) + 1 : 1;
        schedule.setId(maxId);

        schedules.put(maxId, schedule);

        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        return responseDto;
    }

}
