package com.linhv.scheduling.controller;

import com.linhv.scheduling.ga.DNA;
import com.linhv.scheduling.ga.GeneticAlgorithm;
import com.linhv.scheduling.service.ScheduledExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/exams")
public class ScheduleExamController {

    @Autowired
    private ScheduledExamService examService;

    @Autowired
    private GeneticAlgorithm GA;

    @GetMapping("/test/")
    public ResponseEntity<String> testSchedule() {
        this.GA.initAlgorithm(201, new Date(), 7, 20);
        this.GA.evaluatePopulation();

        System.out.println("random parent: ");
        for (DNA dna : this.GA.getPopulation()) {
            System.out.println(dna.getFitness());
        }

        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
