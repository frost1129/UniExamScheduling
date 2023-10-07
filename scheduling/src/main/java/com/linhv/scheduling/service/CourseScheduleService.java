package com.linhv.scheduling.service;

import com.linhv.scheduling.model.CourseSchedule;

import java.util.List;

public interface CourseScheduleService {

//    READ
    CourseSchedule getById(String id);
    List<CourseSchedule> getAllByYearCode(int year);
    List<CourseSchedule> getAllByYearCodeAndFaculty(int year, Long facultyId);
    List<CourseSchedule> getAllByYearCodeAndTeacher(int year, Long teacherId);
    List<CourseSchedule> getAllByYearCodeAndCourse(int year, String courseId);

//    CREATE
    CourseSchedule newSchedule(CourseSchedule cs);

//    UPDATE
    CourseSchedule updateSchedule(String id, CourseSchedule cs);

//    DELETE
    boolean deleteSchedule(String id);
}
