package com.sparta.calendar.service;

import com.sparta.calendar.dto.ScheduleRequestDto;
import com.sparta.calendar.dto.ScheduleResponseDto;
import com.sparta.calendar.entity.Schedule;
import com.sparta.calendar.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto request) {
        Schedule schedule = new Schedule(request);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule);
    }

    @Transactional(readOnly = true)
    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
    }

    @Transactional
    public Schedule updateSchedule(Long id, ScheduleRequestDto request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        if (schedule.getPassword().equals(request.getPassword())) {
            schedule.update(request);
            return scheduleRepository.save(schedule);
        } else {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    @Transactional
    public Long deleteSchedule(Long id, ScheduleRequestDto request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        if (schedule.getPassword().equals(request.getPassword())) {
            scheduleRepository.delete(schedule);
            return id;
        } else {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(ScheduleResponseDto::new).collect(Collectors.toList());
    }
}
