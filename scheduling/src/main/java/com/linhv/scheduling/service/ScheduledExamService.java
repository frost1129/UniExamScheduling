package com.linhv.scheduling.service;

import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.ScheduledExam;
import com.linhv.scheduling.model.User;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduledExamService {

//    READ
    ScheduledExam getExamById(Long id);
    List<ScheduledExam> getExamByDate(Date date);
    List<ScheduledExam> getExamByCourse(String courseId);
    List<Integer> findAllYearCodes();
    List<ScheduledExam> findScheduledExamsByStudentAndYearCode(User student, int yearCode);
    List<ScheduledExam> findScheduledExamsByFacultyAndYearCode(Faculty faculty, int yearCode);
    List<ScheduledExam> findScheduledExamsByYearCode(int yearCode);

//    CREATE
    ScheduledExam newExam(ScheduledExam exam);

//    UPDATE
    ScheduledExam updateExam(Long id, ScheduledExam exam);

//    DELETE
    boolean deleteExam(Long id);
}
