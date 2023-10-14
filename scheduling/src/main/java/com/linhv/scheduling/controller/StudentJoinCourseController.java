package com.linhv.scheduling.controller;

import com.linhv.scheduling.service.StudentJoinCourseService;
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
import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/api/course-register")
public class StudentJoinCourseController {

    @Autowired
    private StudentJoinCourseService joinService;

    @PostMapping(value = "/upload/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadRegisterResult(@RequestParam(name = "file") MultipartFile file,
                                                       @RequestParam(name = "year") Integer yearCode) throws IOException, ParseException {
        this.joinService.importDataFromCsv(file, yearCode);
        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }
}
