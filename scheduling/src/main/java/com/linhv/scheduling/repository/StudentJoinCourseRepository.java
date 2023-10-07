package com.linhv.scheduling.repository;

import com.linhv.scheduling.model.StudentJoinCourse;
import com.linhv.scheduling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentJoinCourseRepository extends JpaRepository<StudentJoinCourse, Long> {
    List<StudentJoinCourse> findByStudent_Id(Long studentId);
    List<StudentJoinCourse> findByCourseSchedule_ScheduleId(String scheduleId);
}
