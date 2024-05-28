package com.sparta.calendar.repository;

import com.sparta.calendar.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByScheduleId(Long scheduleId);
}
