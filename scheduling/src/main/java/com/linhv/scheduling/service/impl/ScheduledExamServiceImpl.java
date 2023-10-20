package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.Course;
import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.ScheduledExam;
import com.linhv.scheduling.model.User;
import com.linhv.scheduling.repository.ScheduledExamRepository;
import com.linhv.scheduling.service.CourseService;
import com.linhv.scheduling.service.ScheduledExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ScheduledExamServiceImpl implements ScheduledExamService {

    @Autowired
    private ScheduledExamRepository examRepo;

    @Autowired
    private CourseService courseService;

    @Override
    public ScheduledExam getExamById(Long id) {
        return examRepo.findById(id).get();
    }

    @Override
    public List<ScheduledExam> getExamByDate(Date date) {
        return examRepo.findByExamDate(date);
    }

    @Override
    public List<ScheduledExam> getExamByCourse(String courseId) {
        Course course = courseService.getById(courseId);
        return examRepo.findByCourseSchedule_Course(course);
    }

    @Override
    public List<Integer> findAllYearCodes() {
        return this.examRepo.findAllYearCodes();
    }

    @Override
    public List<ScheduledExam> findScheduledExamsByStudentAndYearCode(User student, int yearCode) {
        return this.examRepo.findScheduledExamsByStudentAndYearCode(student, yearCode);
    }

    @Override
    public List<ScheduledExam> findScheduledExamsByFacultyAndYearCode(Faculty faculty, int yearCode) {
        return this.examRepo.findScheduledExamsByFacultyAndYearCode(faculty, yearCode);
    }

    @Override
    public List<ScheduledExam> findScheduledExamsByYearCode(int yearCode) {
        return this.examRepo.findScheduledExamsByYearCode(yearCode);
    }

    @Override
    public ScheduledExam newExam(ScheduledExam exam) {
        return examRepo.save(exam);
    }

    @Override
    public void saveAllScheduledExams(List<ScheduledExam> scheduledExams) {
        examRepo.saveAll(scheduledExams);
    }

    @Override
    public ScheduledExam updateExam(Long id, ScheduledExam exam) {
        return null;
    }

    @Override
    public boolean deleteExam(Long id) {
        try {
            ScheduledExam exam = this.getExamById(id);
            examRepo.delete(exam);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
