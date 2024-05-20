package com.sparta.calendar.controller;

import com.sparta.calendar.dto.ScheduleRequestDto;
import com.sparta.calendar.dto.ScheduleResponseDto;
import com.sparta.calendar.entity.Schedule;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/api")
//public class ScheduleController {
//
//    ScheduleServiceTest scheduleService = new ScheduleServiceTest();
//
//    @PostMapping("/schedule")
//    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
//        return scheduleService.createSchedule(requestDto);
//    }
//
//    @GetMapping("/schedules")
//    public List<ScheduleResponseDto> getSchedules() {
//        return scheduleService.getSchedules();
//
//    }
//
//    @GetMapping("/schedule/{id}")
//    public Schedule getSchedule(@PathVariable long id) {
//        return scheduleService.getSchedule(id);
//    }
//
//    @PutMapping("/schedule/{id}")
//    public Schedule updateSchedule(@PathVariable long id, @RequestBody ScheduleRequestDto requestDto) {
//        return scheduleService.updateSchedule(id, requestDto);
//    }
//
//    @DeleteMapping("/schedule/{id}")
//    public long deleteSchedule(@PathVariable long id, @RequestBody ScheduleRequestDto requestDto) {
//        return scheduleService.deleteSchedule(id, requestDto);
//    }
//
//}

import com.sparta.calendar.api_swagger.CommonResponse;
import com.sparta.calendar.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    @ApiResponse(responseCode = "405", description = "잘못된 메소드 요청")
    @ApiResponse(responseCode = "500", description = "처리 방법 오류")
    public CommonResponse<ScheduleResponseDto> createSchedule(@Parameter(required = true, description = "일정 생성 요청") @RequestBody ScheduleRequestDto request) {
        try {
            return CommonResponse.success(scheduleService.createSchedule(request));
        } catch (Exception e) {
            return CommonResponse.fail();
        }
    }

    @GetMapping("/schedules/{id}")
    @Operation(summary = "일정 목록 조회", description = "선택한 일정 목록 조회하기")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    @ApiResponse(responseCode = "405", description = "잘못된 메소드 요청")
    @ApiResponse(responseCode = "500", description = "처리 방법 오류")
    public CommonResponse<Schedule> getSchedule(
            @Parameter(description = "일정 번호, 1부터 시작")
            @PathVariable(required = true) Long id) {
        try {
            return CommonResponse.success(scheduleService.getSchedule(id));
        } catch (Exception e) {
            return CommonResponse.fail();
        }
    }

    @GetMapping("/schedules")
    @Operation(summary = "일정 목록 조회", description = "모든 일정 조회")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    @ApiResponse(responseCode = "405", description = "잘못된 메소드 요청")
    @ApiResponse(responseCode = "500", description = "처리 방법 오류")
    public CommonResponse<List<ScheduleResponseDto>> getSchedules() {
        try {
            return CommonResponse.success(scheduleService.getSchedules());
        } catch (IllegalArgumentException e) {
            return CommonResponse.fail();
        }
    }

    @PutMapping("/schedules/{id}")
    @Operation(summary = "일정 수정", description = "선택한 일정 하나를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    @ApiResponse(responseCode = "405", description = "잘못된 메소드 요청")
    @ApiResponse(responseCode = "500", description = "처리 방법 오류")
    public CommonResponse<Void> updateSchedule(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable Long id,
            @Parameter(required = true, description = "게시글 업데이트 요청")
            @RequestBody
            ScheduleRequestDto request) {
        try {
            scheduleService.updateSchedule(id, request);
            return CommonResponse.success();
        } catch (IllegalArgumentException e) {
            return CommonResponse.fail();
        }
    }

    @DeleteMapping("/schedules/{id}")
    @Operation(summary = "일정 삭제", description = "선택한 일정 하나를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    @ApiResponse(responseCode = "405", description = "잘못된 메소드 요청")
    @ApiResponse(responseCode = "500", description = "처리 방법 오류")
    public CommonResponse<ScheduleResponseDto> deleteSchedule(
            @PathVariable
            @Parameter(required = true, description = "게시글 고유 번호") Long id,
            @RequestBody
            ScheduleRequestDto request) {
        try {
            scheduleService.deleteSchedule(id, request);
            return CommonResponse.success();
        } catch (IllegalArgumentException e) {
            return CommonResponse.fail();
        }
    }

}