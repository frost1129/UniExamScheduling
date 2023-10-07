package com.linhv.scheduling.service;

import com.linhv.scheduling.model.ScheduledExam;

import java.util.Date;
import java.util.List;

public interface ScheduledExamService {

//    READ
    ScheduledExam getExamById(Long id);
    List<ScheduledExam> getExamByDate(Date date);
    List<ScheduledExam> getExamByCourse(String courseId);

//    CREATE
    ScheduledExam newExam(ScheduledExam exam);

//    UPDATE
    ScheduledExam updateExam(Long id, ScheduledExam exam);

//    DELETE
    boolean deleteExam(Long id);
}
