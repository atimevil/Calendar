package com.sparta.calendar.controller;

import com.sparta.calendar.entity.Reply;
import com.sparta.calendar.entity.Schedule;
import com.sparta.calendar.repository.ReplyRepository;
import com.sparta.calendar.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules/{id}/comments")
public class ReplyController {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @PostMapping()
    public ResponseEntity<Reply> createReply(@PathVariable long id, @RequestBody Reply reply) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        if(!scheduleOptional.isPresent()) {
            throw new IllegalArgumentException("Schedule not found");
        }

        Schedule schedule = scheduleOptional.get();
        reply.setSchedule(schedule);
        reply.setCreatedtime(LocalDateTime.now());
        replyRepository.save(reply);
        return ResponseEntity.ok(reply);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Reply> updateReply(@PathVariable long Id, @RequestBody Reply replyDetails) {
        Optional<Reply> replyOptional = replyRepository.findById(Id);
        if(!replyOptional.isPresent()) {
            throw new IllegalArgumentException("Schedule not found");
        }
        Reply reply = replyOptional.get();
        reply.setContent(replyDetails.getContent());
        replyRepository.save(reply);
        return ResponseEntity.ok(reply);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteReply(@PathVariable long Id) {
        Optional<Reply> replyOptional = replyRepository.findById(Id);
        if(!replyOptional.isPresent()) {
            throw new IllegalArgumentException("Schedule not found");
        }

        replyRepository.delete(replyOptional.get());
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }
}
