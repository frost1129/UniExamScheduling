package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.Course;
import com.linhv.scheduling.repository.CourseRepository;
import com.linhv.scheduling.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepo;

    @Override
    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course getById(String id) {
        return courseRepo.findById(id).get();
    }

    @Override
    public Course newCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public Course updateCourse(String id, Course course) {
        return null;
    }

    @Override
    public boolean deleteCourse(String id) {
        try {
            Course course = this.getById(id);
            courseRepo.delete(course);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
