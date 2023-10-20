package com.linhv.scheduling.controller;

import com.linhv.scheduling.service.StudentJoinCourseService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/course-register")
public class StudentJoinCourseController {

    @Autowired
    private StudentJoinCourseService joinService;

    @PostMapping(value = "/upload/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadRegisterResult(@RequestParam(name = "file") MultipartFile file,
                                                       @RequestParam(name = "year") Integer yearCode) throws IOException, ParseException {
        List<String> expectedHeaders = List.of("scheduleId", "studentId", "registerDate");
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            CSVFormat csvFormat = CSVFormat.DEFAULT;

            // Đọc dòng header của file CSV
            try (CSVParser csvParser = new CSVParser(reader, csvFormat)) {
                CSVRecord headerRecord = csvParser.iterator().next();
                List<String> actualHeaders = Arrays.stream(headerRecord.values()).toList();

                if (actualHeaders.equals(expectedHeaders)) {
                    // Nếu khớp header
                    this.joinService.importDataFromCsv(file, yearCode);
                    return new ResponseEntity<>("Success!", HttpStatus.OK);
                } else {
                    // Nếu không khớp header
                    return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
                }
            }
        }

    }
}
