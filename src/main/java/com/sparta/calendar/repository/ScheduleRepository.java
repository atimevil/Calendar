package com.sparta.calendar.repository;

import com.sparta.calendar.entity.Schedule;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ScheduleRepository {
    private Map<Long, Schedule> schedules = new HashMap<>();

    public ScheduleRepository() {
        this.schedules = new HashMap<>();
    }
}
