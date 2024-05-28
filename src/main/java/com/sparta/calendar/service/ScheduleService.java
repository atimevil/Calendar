package com.sparta.calendar.service;

import com.sparta.calendar.dto.ScheduleRequestDto;
import com.sparta.calendar.dto.ScheduleResponseDto;
import com.sparta.calendar.entity.Schedule;
import com.sparta.calendar.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;


    public ScheduleResponseDto createSchedule(ScheduleRequestDto request) {
        Schedule schedule = new Schedule(request);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule);
    }

    public Schedule getSchedule(Long id) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        if (scheduleOptional.isPresent()) {
            return scheduleOptional.get();
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }

    public Schedule updateSchedule(Long id, ScheduleRequestDto request) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        if (scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            if (schedule.getPassword().equals(request.getPassword())) {
                schedule.update(request);
                return scheduleRepository.save(schedule);
            } else {
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }

    public Long deleteSchedule(Long id, ScheduleRequestDto request) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        if (scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            if (schedule.getPassword().equals(request.getPassword())) {
                scheduleRepository.delete(schedule);
                return id;
            } else {
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }

    public List<ScheduleResponseDto> getSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(ScheduleResponseDto::new).collect(Collectors.toList());
    }
}
