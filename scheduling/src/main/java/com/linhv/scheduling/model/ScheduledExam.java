package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_session")
    private int startSession;

    @Column(name = "exam_date")
    private Date examDate;

    @OneToOne
    @JoinColumn(name = "scheduleId")
    private CourseSchedule courseSchedule;
}