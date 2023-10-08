package com.linhv.scheduling.service;

import com.linhv.scheduling.model.Course;

import java.util.List;

public interface CourseService {

//    READ
    List<Course> getAll();
    Course getById(String id);

//    CREATE
    Course newCourse(Course course);
    void importCourseFromCsv(String filePath);

//    UPDATE
    Course updateCourse(String id, Course course);

//    DELETE
    boolean deleteCourse(String id);
}
