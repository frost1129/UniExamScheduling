package com.linhv.scheduling.controller;

import com.linhv.scheduling.ga.DNA;
import com.linhv.scheduling.ga.GeneticAlgorithm;
import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.ScheduledExam;
import com.linhv.scheduling.model.User;
import com.linhv.scheduling.model.dto.SchedulingRequestDTO;
import com.linhv.scheduling.service.FacultyService;
import com.linhv.scheduling.service.ScheduledExamService;
import com.linhv.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Autowired
    private UserService userService;

    @GetMapping("/test/")
    public ResponseEntity<DNA> testSchedule() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.GA.initAlgorithm(211, dateFormat.parse("19-01-2023"), 2, 20);

        System.out.println(this.GA.getBestResult().getFitness());

        while (this.GA.getBestResult().getFitness() > 1) {
            this.GA.doCrossOver(0.2);
        }
        System.out.println(this.GA.getBestResult().getFitness());
//
//        List<ScheduledExam> scheduledExams = new ArrayList<>(this.GA.getBestResult().getExamSchedules().values());
//        this.examService.saveAllScheduledExams(scheduledExams);

        return new ResponseEntity<>(this.GA.getBestResult(), HttpStatus.OK);
    }

    @PostMapping(value = "/implementGA/",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<DNA> scheduling(@ModelAttribute SchedulingRequestDTO request) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.GA.initAlgorithm(request.getYearCode(), dateFormat.parse(request.getDate()), request.getLength(), request.getSize());

        System.out.println(this.GA.getBestResult().getFitness());

        int generation = 1;

        while (this.GA.getBestResult().getFitness() > 1) {
            this.GA.doCrossOver(request.getMutationRate());
            if (generation++ == 100)
                break;
        }

        System.out.println(this.GA.getBestResult().getFitness());

        List<ScheduledExam> scheduledExams = new ArrayList<>(this.GA.getBestResult().getExamSchedules().values());
        this.examService.saveAllScheduledExams(scheduledExams);

//        List<ScheduledExam> scheduledExams = new ArrayList<>(this.GA.getBestResult().getExamSchedules().values());
//        this.examService.saveAllScheduledExams(scheduledExams);

        return new ResponseEntity<>(this.GA.getBestResult(), HttpStatus.OK);
    }

    @PostMapping("/addExamSchedule/")
    public ResponseEntity<String> addSchedule(@ModelAttribute DNA dna) {
        List<ScheduledExam> scheduledExams = new ArrayList<>(dna.getExamSchedules().values());
        this.examService.saveAllScheduledExams(scheduledExams);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @GetMapping("/getYearCodes/")
    public ResponseEntity<List<Integer>> findAllYearCodes() {
        return new ResponseEntity<>(this.examService.findAllYearCodes(), HttpStatus.OK);
    }

    @GetMapping("/getByYear/{yearCode}")
    public ResponseEntity<List<ScheduledExam>> getExamsByYearCode(
            @PathVariable(name = "yearCode") int yearCode,
            Principal principal
    ) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName()));
        return new ResponseEntity<>(this.examService.findScheduledExamsByStudentAndYearCode(user, yearCode), HttpStatus.OK);
    }

    @GetMapping("/getByFacultyAndYear/")
    public ResponseEntity<List<ScheduledExam>> getExamsByFacultyAndYearcode(
            @RequestParam(name = "facultyId") Long facultyId,
            @RequestParam(name = "yearCode") int yearCode
    ) {
        Faculty faculty = this.facultyService.getById(facultyId);
        return new ResponseEntity<>(this.examService.findScheduledExamsByFacultyAndYearCode(faculty, yearCode), HttpStatus.OK);
    }

    @GetMapping("/getMaxDay/{yearCode}")
    public ResponseEntity<Date> maxDateByYear(@PathVariable(name = "yearCode") int yearCode) {
        return new ResponseEntity<>(this.examService.getLargestDay(yearCode), HttpStatus.OK);
    }

    @GetMapping("/getMinDay/{yearCode}")
    public ResponseEntity<Date> minDateByYear(@PathVariable(name = "yearCode") int yearCode) {
        return new ResponseEntity<>(this.examService.getSmallestDay(yearCode), HttpStatus.OK);
    }
}
