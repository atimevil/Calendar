package com.sparta.calendar.service;

import com.sparta.calendar.dto.*;
import com.sparta.calendar.entity.Schedule;
import com.sparta.calendar.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository schedules = new ScheduleRepository();


    public ScheduleResponseDto createSchedule(ScheduleRequestDto request) {
        Schedule schedule = new Schedule(request);

        long maxId = schedules.getSchedules().size() > 0 ? Collections.max(schedules.getSchedules().keySet()) + 1 : 1;
        schedule.setId(maxId);

        schedules.getSchedules().put(maxId, schedule);

        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        return responseDto;
    }

    public Schedule getSchedule(Long id) {
        if (schedules.getSchedules().containsKey(id)) {
            Schedule schedule = schedules.getSchedules().get(id);

            return schedules.getSchedules().get(schedule.getId());
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }

    public Schedule updateSchedule(Long id, ScheduleRequestDto request) {
        if (schedules.getSchedules().containsKey(id)) {
            Schedule schedule = schedules.getSchedules().get(id);
            if (schedule.getPassword().equals(request.getPassword())) {
                schedule.update(request);
                return schedule;
            } else {
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }

    public Long deleteSchedule(Long id, ScheduleRequestDto request) {
        if (schedules.getSchedules().containsKey(id)) {
            Schedule schedule = schedules.getSchedules().get(id);
            if (schedule.getPassword().equals(request.getPassword())) {
                schedules.getSchedules().remove(id);
                return id;
            } else {
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }

    }

    public List<ScheduleResponseDto> getSchedules(Long id) {
        List<ScheduleResponseDto> scheduleList = schedules.getSchedules().values().stream().map(ScheduleResponseDto::new).toList();
        return scheduleList;
    }

    public List<ScheduleResponseDto> getSchedules() {
        List<ScheduleResponseDto> scheduleList = schedules.getSchedules().values().stream().map(ScheduleResponseDto::new).toList();

        return scheduleList;
    }
}
