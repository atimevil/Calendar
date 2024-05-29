package com.sparta.calendar.service;

import com.sparta.calendar.dto.ReplyRequestDto;
import com.sparta.calendar.dto.ReplyResponseDto;
import com.sparta.calendar.entity.Reply;
import com.sparta.calendar.entity.Schedule;
import com.sparta.calendar.entity.User;
import com.sparta.calendar.repository.ReplyRepository;
import com.sparta.calendar.repository.ScheduleRepository;
import com.sparta.calendar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReplyResponseDto createReply(ReplyRequestDto requestDto, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        User author = userRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Reply reply = new Reply();
        reply.setContent(requestDto.getContent());
        reply.setSchedule(schedule);
        reply.setAuthor(author);
        reply.setCreatedtime(LocalDateTime.now());
        replyRepository.save(reply);
        return new ReplyResponseDto(reply.getId(), reply.getContent(), reply.getAuthor().getUsername());
    }

    @Transactional
    public ReplyResponseDto updateReply(Long replyId, ReplyRequestDto requestDto) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("Reply not found"));
        User author = userRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (reply.getAuthor().equals(author)) {
            reply.setContent(requestDto.getContent());
            replyRepository.save(reply);
        } else {
            throw new IllegalArgumentException("Wrong author");
        }
        return new ReplyResponseDto(reply.getId(), reply.getContent(), reply.getAuthor().getUsername());
    }

    @Transactional
    public void deleteReply(Long replyId, Long authorId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("Reply not found"));
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (reply.getAuthor().equals(author)) { // 작성자 확인
            replyRepository.delete(reply);
        } else {
            throw new IllegalArgumentException("You are not the author of this reply");
        }
    }


}
