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
//        User newU = new User("admin@ou.edu.vn", "Linh", "Vũ", new Date(2002, Calendar.NOVEMBER, 29), User.FEMALE, User.ADMIN, facultyRepo.getReferenceById(2L));
//        System.out.println(userService.createUser(newU));

        return "Hello world!";
    }

    @GetMapping("/import")
    public String importCsv() {
        String filePath = "src/main/resources/datas/teachers.csv";
        userService.importUserFromCsv(filePath);
        return "imported";
    }
}
