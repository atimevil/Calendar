package com.sparta.calendar.service;

import com.sparta.calendar.dto.ReplyRequestDto;
import com.sparta.calendar.dto.ReplyResponseDto;
import com.sparta.calendar.entity.Reply;
import com.sparta.calendar.entity.Schedule;
import com.sparta.calendar.repository.ReplyRepository;
import com.sparta.calendar.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ReplyResponseDto createReply(ReplyRequestDto requestDto, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        Reply reply = new Reply();
        reply.setContent(requestDto.getContent());
        reply.setSchedule(schedule);
        reply.setCreatedtime(LocalDateTime.now());
        replyRepository.save(reply);
        return new ReplyResponseDto(reply.getId(), reply.getContent());
    }

    @Transactional
    public ReplyResponseDto updateReply(Long replyId, ReplyRequestDto requestDto) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("Reply not found"));
        reply.setContent(requestDto.getContent());
        replyRepository.save(reply);
        return new ReplyResponseDto(reply.getId(), reply.getContent());
    }

    @Transactional
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("Reply not found"));
        replyRepository.delete(reply);
    }


}
