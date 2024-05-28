package com.sparta.calendar.service;

import com.sparta.calendar.dto.ReplyRequestDto;
import com.sparta.calendar.dto.ReplyResponseDto;
import com.sparta.calendar.entity.Reply;
import com.sparta.calendar.entity.Schedule;
import com.sparta.calendar.repository.ReplyRepository;
import com.sparta.calendar.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ScheduleRepository scheduleRepository;

    public ReplyResponseDto createReply(ReplyRequestDto requestDto, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        Reply reply = new Reply();
        reply.setContent(requestDto.getContent());
        reply.setSchedule(schedule);
        replyRepository.save(reply);
        return new ReplyResponseDto(reply.getId(), reply.getContent());
    }

//    public Reply getReply(Long replyId) {
//        return replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("Reply not found"));
//    }

    public ReplyResponseDto updateReply(Long replyId, ReplyRequestDto requestDto) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("Reply not found"));
        reply.setContent(requestDto.getContent());
        replyRepository.save(reply);
        return new ReplyResponseDto(reply.getId(), reply.getContent());
    }

    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

//    public List<Reply> getReplies(Long scheduleId) {
//        List<Reply> replies = replyRepository.findAll();
//        return replies;
//    }

}
