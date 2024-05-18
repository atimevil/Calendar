//package com.sparta.calendar.service;
//
//import com.sparta.calendar.dto.ScheduleRequestDto;
//import com.sparta.calendar.dto.ScheduleResponseDto;
//import com.sparta.calendar.entity.Schedule;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ScheduleServiceTest {
//
//    private final Map<Long, Schedule> schedules = new HashMap<>();
//
//    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
//        Schedule schedule = new Schedule(requestDto);
//
//        long maxId = schedules.size() > 0 ? Collections.max(schedules.keySet()) + 1 : 1;
//        schedule.setId(maxId);
//
//        schedules.put(maxId, schedule);
//
//        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
//        return responseDto;
//    }
//
//    public List<ScheduleResponseDto> getSchedules() {
//        List<ScheduleResponseDto> scheduleList = schedules.values().stream().map(ScheduleResponseDto::new).toList();
//
//        return scheduleList; // 작성일 기준 내림차순으로 정렬되게 해야함
//    }
//
//    public Schedule getSchedule(long id) {
//        if (schedules.containsKey(id)) {
//            Schedule schedule = schedules.get(id);
//
//            return schedules.get(schedule.getId());
//        } else {
//            throw new IllegalArgumentException("Schedule not found");
//        }
//    }
//
//    public Schedule updateSchedule(long id, ScheduleRequestDto requestDto) {
//        if (schedules.containsKey(id)) {
//            Schedule schedule = schedules.get(id);
//            if (schedule.getPassword().equals(requestDto.getPassword())) {
//                schedule.update(requestDto);
//                return schedule;
//            } else {
//                throw new IllegalArgumentException("Passwords don't match");
//            }
//        } else {
//            throw new IllegalArgumentException("Schedule not found");
//        }
//    }
//
//    public long deleteSchedule(long id, ScheduleRequestDto requestDto) {
//        if (schedules.containsKey(id)) {
//            Schedule schedule = schedules.get(id);
//            if (schedule.getPassword().equals(requestDto.getPassword())) {
//                schedules.remove(id);
//                return id;
//            } else {
//                throw new IllegalArgumentException("Passwords don't match");
//            }
//        } else {
//            throw new IllegalArgumentException("Schedule not found");
//        }
//    }
//}
