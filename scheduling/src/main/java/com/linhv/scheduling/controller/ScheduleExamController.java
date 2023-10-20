package com.linhv.scheduling.controller;

import com.linhv.scheduling.ga.DNA;
import com.linhv.scheduling.ga.GeneticAlgorithm;
import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.ScheduledExam;
import com.linhv.scheduling.service.FacultyService;
import com.linhv.scheduling.service.ScheduledExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ScheduleExamController {

    @Autowired
    private ScheduledExamService examService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private GeneticAlgorithm GA;

    @GetMapping("/test/")
    public ResponseEntity<DNA> testSchedule() {
        this.GA.initAlgorithm(201, new Date(), 7, 20);

        System.out.println(this.GA.getBestResult().getFitness());

        while (this.GA.getBestResult().getFitness() > 1) {
            this.GA.doCrossOver(0.1);
        }
        System.out.println(this.GA.getBestResult().getFitness());

        return new ResponseEntity<>(this.GA.getBestResult(), HttpStatus.OK);
    }

    @GetMapping("/getYearCodes/")
    public ResponseEntity<List<Integer>> findAllYearCodes() {
        return new ResponseEntity<>(this.examService.findAllYearCodes(), HttpStatus.OK);
    }

    @GetMapping("/getByYear/{yearCode}")
    public ResponseEntity<List<ScheduledExam>> getExamsByYearCode(@PathVariable(name = "yearCode") int yearCode) {
        return new ResponseEntity<>(this.examService.findScheduledExamsByYearCode(yearCode), HttpStatus.OK);
    }

    @GetMapping("/getByFacultyAndYear/")
    public ResponseEntity<List<ScheduledExam>> getExamsByFacultyAndYearcode(
            @RequestParam(name = "facultyId") Long facultyId,
            @RequestParam(name = "yearCode") int yearCode
    ) {
        Faculty faculty = this.facultyService.getById(facultyId);
        return new ResponseEntity<>(this.examService.findScheduledExamsByFacultyAndYearCode(faculty, yearCode), HttpStatus.OK);
    }
}
