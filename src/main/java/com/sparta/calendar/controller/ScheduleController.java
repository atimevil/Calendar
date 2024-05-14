package com.sparta.calendar.controller;

import com.sparta.calendar.dto.ScheduleRequestDto;
import com.sparta.calendar.dto.ScheduleResponseDto;
import com.sparta.calendar.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/calendars")
    public List<ScheduleResponseDto> getSchedules() {
        List<ScheduleResponseDto> scheduleList = schedules.values().stream().map(ScheduleResponseDto::new).toList();

        return scheduleList;
    }

}
