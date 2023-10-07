package com.linhv.scheduling.repository;

import com.linhv.scheduling.model.Course;
import com.linhv.scheduling.model.CourseSchedule;
import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, String> {
    List<CourseSchedule> findByYearCode(int yearCode);
    List<CourseSchedule> findByYearCodeAndFaculty(int yearCode, Faculty faculty);
    List<CourseSchedule> findByYearCodeAndTeacher(int yearCode, User teacher);
    List<CourseSchedule> findByYearCodeAndCourse(int yearCode, Course course);
}
