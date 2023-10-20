package com.linhv.scheduling.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "exam_date")
    private Date examDate;

    @ManyToOne()
    @JoinColumn(name = "time_slot")
    private TimeSlot timeSlot;

    @OneToOne
    @JoinColumn(name = "scheduleId")
    private CourseSchedule courseSchedule;

    public ScheduledExam(ScheduledExam scheduledExam) {
        this.examDate = scheduledExam.getExamDate();
        this.timeSlot = scheduledExam.getTimeSlot();
        this.courseSchedule = scheduledExam.getCourseSchedule();
    }
}
