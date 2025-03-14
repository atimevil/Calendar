package com.sparta.calendar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.calendar.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Length(max = 200)
    @NotBlank
    private String title;

    @Column
    private String description;

    private String author;
    private LocalDateTime createdtime;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reply> replies;

    public Schedule(ScheduleRequestDto request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.author = request.getAuthor();
        this.password = request.getPassword();
        this.createdtime = LocalDateTime.now();
    }

    public void update(ScheduleRequestDto request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.author = request.getAuthor();
        this.password = request.getPassword();
    }
}