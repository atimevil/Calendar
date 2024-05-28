package com.sparta.calendar.controller;

import com.sparta.calendar.api_swagger.CommonResponse;
import com.sparta.calendar.dto.ScheduleRequestDto;
import com.sparta.calendar.dto.ScheduleResponseDto;
import com.sparta.calendar.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Schedule", description = "Schedule API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @Operation(summary = "일정 생성", description = "일정 생성하기")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    @ApiResponse(responseCode = "405", description = "잘못된 메소드 요청")
    @ApiResponse(responseCode = "500", description = "처리 방법 오류")
    public CommonResponse<ScheduleResponseDto> createSchedule(@Parameter(required = true, description = "일정 생성 요청") @RequestBody ScheduleRequestDto request) {
        try {
            ScheduleResponseDto responseDto = scheduleService.createSchedule(request);
            return CommonResponse.success(responseDto);
        } catch (Exception e) {
            return CommonResponse.fail();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "일정 조회", description = "선택한 일정 조회하기")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    @ApiResponse(responseCode = "405", description = "잘못된 메소드 요청")
    @ApiResponse(responseCode = "500", description = "처리 방법 오류")
    public CommonResponse<ScheduleResponseDto> getSchedule(
            @Parameter(description = "일정 번호, 1부터 시작")
            @PathVariable Long id) {
        try {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(scheduleService.getSchedule(id));
            return CommonResponse.success(responseDto);
        } catch (Exception e) {
            return CommonResponse.fail();
        }
    }

    @GetMapping
    @Operation(summary = "일정 목록 조회", description = "모든 일정 조회")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    @ApiResponse(responseCode = "405", description = "잘못된 메소드 요청")
    @ApiResponse(responseCode = "500", description = "처리 방법 오류")
    public CommonResponse<List<ScheduleResponseDto>> getSchedules() {
        try {
            List<ScheduleResponseDto> schedules = scheduleService.getSchedules();
            return CommonResponse.success(schedules);
        } catch (IllegalArgumentException e) {
            return CommonResponse.fail();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "일정 수정", description = "선택한 일정 하나를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    @ApiResponse(responseCode = "405", description = "잘못된 메소드 요청")
    @ApiResponse(responseCode = "500", description = "처리 방법 오류")
    public CommonResponse<ScheduleResponseDto> updateSchedule(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable Long id,
            @Parameter(required = true, description = "게시글 업데이트 요청")
            @RequestBody ScheduleRequestDto request) {
        try {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(scheduleService.updateSchedule(id, request));
            return CommonResponse.success(responseDto);
        } catch (IllegalArgumentException e) {
            return CommonResponse.fail();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "일정 삭제", description = "선택한 일정 하나를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    @ApiResponse(responseCode = "405", description = "잘못된 메소드 요청")
    @ApiResponse(responseCode = "500", description = "처리 방법 오류")
    public CommonResponse<Long> deleteSchedule(
            @PathVariable
            @Parameter(required = true, description = "게시글 고유 번호") Long id,
            @RequestBody
            ScheduleRequestDto request) {
        try {
            Long deletedId = scheduleService.deleteSchedule(id, request);
            return CommonResponse.success(deletedId);
        } catch (IllegalArgumentException e) {
            return CommonResponse.fail();
        }
    }
}
