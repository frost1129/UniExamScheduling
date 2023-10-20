package com.linhv.scheduling.controller;

import com.linhv.scheduling.model.CourseSchedule;
import com.linhv.scheduling.model.User;
import com.linhv.scheduling.service.CourseScheduleService;
import com.linhv.scheduling.service.StudentJoinCourseService;
import com.linhv.scheduling.service.UserService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class CourseScheduleController {

    @Autowired
    private CourseScheduleService scheduleService;

    @Autowired
    private StudentJoinCourseService joinService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/upload/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> uploadSchedules(@RequestParam(name = "file") MultipartFile file,
                                                  @RequestParam(name = "year") Integer yearCode,
                                                  @RequestParam(name = "date") Date startDate) throws IOException {
        List<String> expectedHeaders = List.of("facultyId", "courseId", "teacherId", "scheduleId", "day", "sessionS");
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            CSVFormat csvFormat = CSVFormat.DEFAULT;

            // Đọc dòng header của file CSV
            try (CSVParser csvParser = new CSVParser(reader, csvFormat)) {
                CSVRecord headerRecord = csvParser.iterator().next();
                List<String> actualHeaders = Arrays.stream(headerRecord.values()).toList();

                if (actualHeaders.equals(expectedHeaders)) {
                    // Nếu khớp
                    this.scheduleService.importScheduleFromCsv(file, yearCode, startDate);
                    return new ResponseEntity<>( "Success!", HttpStatus.OK);
                } else {
                    // Nếu không khớp
                    return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
                }
            }
        }
    }

    @GetMapping("/getByUserAndYear/{yearCode}")
    public ResponseEntity<List<CourseSchedule>> getScheduleByUserAndYearcode(
                        Principal principal,
                        @PathVariable(name = "yearCode") int yearCode) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName()));
        if (user.getRole().equals(User.TEACHER))
            return new ResponseEntity<>(this.scheduleService.getAllByYearCodeAndTeacher(yearCode, user.getId()), HttpStatus.OK);
        if (user.getRole().equals(User.STUDENT))
            return new ResponseEntity<>(this.joinService.getAllScheduleByStudentAndYearCode(user, yearCode), HttpStatus.OK);

        // admin không có course-schedule
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }

    @GetMapping("/getYearCodes/")
    public ResponseEntity<List<Integer>> getAllYearCode() {
        return new ResponseEntity<>(this.scheduleService.findAllYearCodes(), HttpStatus.OK);
    }

    @GetMapping("/getByFacultyAndYear/")
    public ResponseEntity<List<CourseSchedule>> getSchedulesByFacultyAndYearcode(
                        @RequestParam(name = "facultyId") Long facultyId,
                        @RequestParam(name = "yearCode") int yearCode
    ) {
        return new ResponseEntity<>(this.scheduleService.getAllByYearCodeAndFaculty(yearCode, facultyId), HttpStatus.OK);
    }
}
