package com.linhv.scheduling.repository;

import com.linhv.scheduling.model.Course;
import com.linhv.scheduling.model.ScheduledExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduledExamRepository extends JpaRepository<ScheduledExam, Long> {
    List<ScheduledExam> findByExamDate(Date date);
    List<ScheduledExam> findByCourseSchedule_Course(Course course);
}
