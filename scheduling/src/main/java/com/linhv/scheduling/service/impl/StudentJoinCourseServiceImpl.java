package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.StudentJoinCourse;
import com.linhv.scheduling.repository.StudentJoinCourseRepository;
import com.linhv.scheduling.service.StudentJoinCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentJoinCourseServiceImpl implements StudentJoinCourseService {

    @Autowired
    StudentJoinCourseRepository joinRepo;

    @Override
    public StudentJoinCourse getById(Long id) {
        return joinRepo.findById(id).get();
    }

    @Override
    public List<StudentJoinCourse> getAllByStudent(Long studentId) {
        return joinRepo.findByStudent_Id(studentId);
    }

    @Override
    public List<StudentJoinCourse> getAllByCourse(String courseId) {
        return joinRepo.findByCourseSchedule_ScheduleId(courseId);
    }

    @Override
    public StudentJoinCourse newJoin(StudentJoinCourse join) {
        return joinRepo.save(join);
    }

    @Override
    public StudentJoinCourse updateJoin(Long sjcId, StudentJoinCourse join) {
        return null;
    }

    @Override
    public boolean deleteJoin(Long id) {
        try {
            StudentJoinCourse join = this.getById(id);
            joinRepo.delete(join);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
