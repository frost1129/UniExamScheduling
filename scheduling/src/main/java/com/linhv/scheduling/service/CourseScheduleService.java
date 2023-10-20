package com.linhv.scheduling.service;

import com.linhv.scheduling.model.CourseSchedule;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface CourseScheduleService {

//    READ
    CourseSchedule getById(String id);
    List<CourseSchedule> getAllByYearCode(int year);
    List<CourseSchedule> getAllByYearCodeAndFaculty(int year, Long facultyId);
    List<CourseSchedule> getAllByYearCodeAndTeacher(int year, Long teacherId);
    List<CourseSchedule> getAllByYearCodeAndCourse(int year, String courseId);
    List<Integer> findAllYearCodes();

//    CREATE
    CourseSchedule newSchedule(CourseSchedule cs);
    void importScheduleFromCsv(MultipartFile file, int yearCode, Date dateStart) throws IOException;

//    UPDATE
    CourseSchedule updateSchedule(String id, CourseSchedule cs);

//    DELETE
    boolean deleteSchedule(String id);
}
