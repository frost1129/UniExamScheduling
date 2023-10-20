package com.linhv.scheduling.repository;

import com.linhv.scheduling.model.CourseSchedule;
import com.linhv.scheduling.model.StudentJoinCourse;
import com.linhv.scheduling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentJoinCourseRepository extends JpaRepository<StudentJoinCourse, Long> {
    List<StudentJoinCourse> findByStudent_Id(Long studentId);
    List<StudentJoinCourse> findByCourseSchedule_ScheduleId(String scheduleId);

    @Query("SELECT sjc.courseSchedule FROM StudentJoinCourse sjc " +
            "WHERE sjc.student = :student AND sjc.courseSchedule.yearCode = :yearCode")
    List<CourseSchedule> findCourseSchedulesByStudentAndYearCode(@Param("student") User student, @Param("yearCode") int yearCode);
}
