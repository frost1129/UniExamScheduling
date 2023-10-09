package com.linhv.scheduling.service;

import com.linhv.scheduling.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

//    READ
    List<User> getAllUser();
    List<User> getAllTeacherByFacultyId(Long facultyId);
    List<User> getAllStudentByFacultyId(Long facultyId);
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllStudentByCourseAndYearCode(String courseId, int year);

//    CREATE
    User createUser(User u);
    void importUserFromCsv(String filePath);

    void createAccountIfNotExists(User u);
    void processUsersWithoutAccount();

//    UPDATE
    User updateUser(Long id, User newUserData);

//    DELETE
    boolean deleteUser(Long id);
}
