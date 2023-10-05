package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "course_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSchedule {

    @Id
    @Column(name = "schedule_id")
    private String scheduleId;

    @Column(name = "weekday")
    private int weekday;

    @Column(name = "session_start")
    private int sessionStart;

    @Column(name = "session_length")
    private int sessionLength;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "course_length")
    private int courseLength;

    @Column(name = "year_code")
    private int yearCode;

    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "faculty")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    @OneToOne(mappedBy = "courseSchedule")
    private ScheduledExam scheduledExam;
}
