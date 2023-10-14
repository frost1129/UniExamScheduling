package com.linhv.scheduling.service;

import com.linhv.scheduling.model.StudentJoinCourse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface StudentJoinCourseService {

//    READ
    StudentJoinCourse getById(Long id);
    List<StudentJoinCourse> getAllByStudent(Long studentId);
    List<StudentJoinCourse> getAllByCourse(String courseId);

//    CREATE
    StudentJoinCourse newJoin(StudentJoinCourse join);
    void importDataFromCsv(MultipartFile file, int yearCode) throws IOException, ParseException;

//    UPDATE
    StudentJoinCourse updateJoin(Long sjcId, StudentJoinCourse join);

//    DELETE
    boolean deleteJoin(Long id);
}
