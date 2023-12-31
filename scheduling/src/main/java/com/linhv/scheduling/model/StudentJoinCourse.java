package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "student_join_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentJoinCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "total_score")
    private Float totalScore;

    @ManyToOne
    @JoinColumn(name = "student")
    private User student;

    @ManyToOne
    @JoinColumn(name = "course_schedule")
    private CourseSchedule courseSchedule;

    public boolean isEqualStudent(StudentJoinCourse sjc) {
        return this.student == sjc.getStudent();
    }
}
