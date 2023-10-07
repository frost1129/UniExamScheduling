package com.linhv.scheduling.controller;

import com.linhv.scheduling.model.User;
import com.linhv.scheduling.repository.FacultyRepository;
import com.linhv.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    FacultyRepository facultyRepo;

    @GetMapping("/")
    public String index() {
        return "Hello world!";
    }

    @GetMapping("/import")
    public String importCsv() {
        String filePath = "src/main/resources/datas/students.csv";
        userService.importUserFromCsv(filePath);
        return "imported";
    }
}
