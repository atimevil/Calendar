package com.sparta.calendar.controller;

import com.sparta.calendar.api_swagger.CommonResponse;
import com.sparta.calendar.dto.PostUpdateRequest;
import com.sparta.calendar.dto.ScheduleCreateRequest;
import com.sparta.calendar.dto.ScheduleCreateResponse;
import com.sparta.calendar.dto.ScheduleReadResponse;
import com.sparta.calendar.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Schedule", description = "Schedule API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    @Operation(summary = "일정 생성", description = "일정 생성하기")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public CommonResponse<ScheduleCreateResponse> createSchedule(@Parameter(required = true, description = "일정 생성 요청") @RequestBody ScheduleCreateRequest request) {
        return CommonResponse.success(scheduleService.createSchedule(request));
    }

    @GetMapping("/schedules")
    @Operation(summary = "일정 목록 조회", description = "선택한 일정 목록 조회하기")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public CommonResponse<ScheduleReadResponse> getSchedule(
            @Parameter(description = "일정 번호, 1부터 시작")
            @RequestParam(required = true) Long id) {
        return CommonResponse.success(scheduleService.getSchedule(id));

    }

    @PutMapping("/schedules/{id}")
    @Operation(summary = "일정 수정", description = "선택한 일정 하나를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public CommonResponse<Void> updateSchedule(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable Long id,
            @Parameter(required = true, description = "게시글 업데이트 요청")
            @RequestBody
            PostUpdateRequest request) {
        scheduleService.updateSchedule(id, request);
        return CommonResponse.success();
    }

    @DeleteMapping("/schedules/{id}")
    @Operation(summary = "일정 삭제", description = "선택한 일정 하나를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public CommonResponse<ScheduleReadResponse> deleteSchedule(
            @PathVariable
            @Parameter(required = true, description = "게시글 고유 번호") Long id) {
        scheduleService.deleteSchedule(id);
        return CommonResponse.success();
    }

}

//package com.sparta.calendar.controller;
//
//import com.sparta.calendar.dto.ScheduleRequestDto;
//import com.sparta.calendar.dto.ScheduleResponseDto;
//import com.sparta.calendar.entity.Schedule;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class ScheduleController {
//
//    private final Map<Long, Schedule> schedules = new HashMap<>();
//
//    @PostMapping("/schedule")
//    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
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
//    @GetMapping("/schedules")
//    public List<ScheduleResponseDto> getSchedules() {
//        List<ScheduleResponseDto> scheduleList = schedules.values().stream().map(ScheduleResponseDto::new).toList();
//
//        return scheduleList; // 작성일 기준 내림차순으로 정렬되게 해야함
//    }
//
//    @GetMapping("/schedule/{id}")
//    public Schedule getSchedule(@PathVariable long id) {
//        if (schedules.containsKey(id)) {
//            Schedule schedule = schedules.get(id);
//
//            return schedules.get(schedule.getId());
//        } else {
//            throw new IllegalArgumentException("Schedule not found");
//        }
//    }
//
//    @PutMapping("/schedule/{id}")
//    public Schedule updateSchedule(@PathVariable long id, @RequestBody ScheduleRequestDto requestDto) {
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
//    @DeleteMapping("/schedule/{id}")
//    public long deleteSchedule(@PathVariable long id, @RequestBody ScheduleRequestDto requestDto) {
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
//
//}
