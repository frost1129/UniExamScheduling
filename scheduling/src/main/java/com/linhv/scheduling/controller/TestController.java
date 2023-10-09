package com.linhv.scheduling.controller;

import com.linhv.scheduling.model.User;
import com.linhv.scheduling.repository.FacultyRepository;
import com.linhv.scheduling.service.AccountService;
import com.linhv.scheduling.service.CourseService;
import com.linhv.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    FacultyRepository facultyRepo;

    @Autowired
    CourseService courseService;

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public String index() {
        return "Hello world!";
    }

    @GetMapping("/import")
    public String importCsv() {
        String filePath = "src/main/resources/datas/courses.csv";
        userService.processUsersWithoutAccount();
//        userService.importUserFromCsv(filePath);
//        courseService.importCourseFromCsv(filePath);
        return "imported";
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser() {
        User user = userService.createUser(new User("testmail", "aaa", "aaa", new Date(), User.MALE, User.ADMIN, facultyRepo.findById(1L).get()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllTest() {
        return new ResponseEntity<>(userService.getAllTeacherByFacultyId(1L), HttpStatus.OK);
    }

    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        if (userService.deleteUser(userId))
            return "deleted";
        return "not deleted";
    }
}
