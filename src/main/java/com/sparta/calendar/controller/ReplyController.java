package com.sparta.calendar.controller;

import com.sparta.calendar.api_swagger.CommonResponse;
import com.sparta.calendar.dto.ReplyRequestDto;
import com.sparta.calendar.dto.ReplyResponseDto;
import com.sparta.calendar.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedules/{scheduleId}/replies")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public CommonResponse<ReplyResponseDto> createReply(@PathVariable long scheduleId, @RequestBody ReplyRequestDto reply) {
        return CommonResponse.success(replyService.createReply(reply, scheduleId));
    }

    @PutMapping("/{id}")
    public CommonResponse<Void> updateReply(@PathVariable long id, @RequestBody ReplyRequestDto replyDetails) {
        replyService.updateReply(id, replyDetails);
        return CommonResponse.success();
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Void> deleteReply(@PathVariable long id, @RequestParam Long authorId) {
        replyService.deleteReply(id, authorId);
        return CommonResponse.success();
    }
}
