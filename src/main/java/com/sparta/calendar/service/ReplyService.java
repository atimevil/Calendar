package com.sparta.calendar.service;

import com.sparta.calendar.dto.ReplyRequestDto;
import com.sparta.calendar.dto.ReplyResponseDto;
import com.sparta.calendar.entity.Reply;
import com.sparta.calendar.entity.Schedule;
import com.sparta.calendar.repository.ReplyRepository;
import com.sparta.calendar.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ScheduleRepository scheduleRepository;

    public ReplyResponseDto addReply(Long scheduleId, ReplyRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        Reply reply = new Reply();
        reply.setContent(requestDto.getContent());
        reply.setSchedule(schedule);
        replyRepository.save(reply);
        return new ReplyResponseDto(reply.getId(), reply.getContent());
    }

}
