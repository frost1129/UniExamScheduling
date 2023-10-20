package com.linhv.scheduling.repository;

import com.linhv.scheduling.model.Course;
import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.ScheduledExam;
import com.linhv.scheduling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduledExamRepository extends JpaRepository<ScheduledExam, Long> {
    List<ScheduledExam> findByExamDate(Date date);
    List<ScheduledExam> findByCourseSchedule_Course(Course course);

    @Query("SELECT DISTINCT se.courseSchedule.yearCode FROM ScheduledExam se")
    List<Integer> findAllYearCodes();

    @Query("SELECT se FROM ScheduledExam se " +
            "WHERE se.courseSchedule.yearCode = :yearCode " +
            "AND se.courseSchedule IN (SELECT sjc.courseSchedule FROM StudentJoinCourse sjc WHERE sjc.student = :student)")
    List<ScheduledExam> findScheduledExamsByStudentAndYearCode(
            @Param("student") User student,
            @Param("yearCode") int yearCode
    );

    @Query("SELECT se FROM ScheduledExam se " +
            "WHERE se.courseSchedule.faculty = :faculty AND se.courseSchedule.yearCode = :yearCode")
    List<ScheduledExam> findScheduledExamsByFacultyAndYearCode(
            @Param("faculty") Faculty faculty,
            @Param("yearCode") int yearCode
    );

    @Query("SELECT se FROM ScheduledExam se WHERE se.courseSchedule.yearCode = :yearCode")
    List<ScheduledExam> findScheduledExamsByYearCode(@Param("yearCode") int yearCode);

    @Query("SELECT MAX(se.examDate) FROM ScheduledExam se WHERE RIGHT(se.courseSchedule.scheduleId, 3) = :suffix")
    Date findMaxExamDateWithCustomSuffix(@Param("suffix") String suffix);

    @Query("SELECT MIN(se.examDate) FROM ScheduledExam se WHERE RIGHT(se.courseSchedule.scheduleId, 3) = :suffix")
    Date findMinExamDateWithCustomSuffix(@Param("suffix") String suffix);
}
