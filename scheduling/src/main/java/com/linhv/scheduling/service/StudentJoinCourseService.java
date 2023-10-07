package com.linhv.scheduling.service;

import com.linhv.scheduling.model.StudentJoinCourse;

import java.util.List;

public interface StudentJoinCourseService {

//    READ
    StudentJoinCourse getById(Long id);
    List<StudentJoinCourse> getAllByStudent(Long studentId);
    List<StudentJoinCourse> getAllByCourse(String courseId);

//    CREATE
    StudentJoinCourse newJoin(StudentJoinCourse join);

//    UPDATE
    StudentJoinCourse updateJoin(Long sjcId, StudentJoinCourse join);

//    DELETE
    boolean deleteJoin(Long id);
}
