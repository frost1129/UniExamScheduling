package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.Account;
import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.User;
import com.linhv.scheduling.repository.UserRepository;
import com.linhv.scheduling.service.AccountService;
import com.linhv.scheduling.service.FacultyService;
import com.linhv.scheduling.service.UserService;
import jakarta.persistence.NoResultException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private AccountService accountService;

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getAllTeacherByFacultyId(Long facultyId) {
        Faculty faculty = facultyService.getById(facultyId);
        return userRepo.findByFacultyAndRole(faculty, User.TEACHER);
    }

    @Override
    public List<User> getAllStudentByFacultyId(Long facultyId) {
        Faculty faculty = facultyService.getById(facultyId);
        return userRepo.findByFacultyAndRole(faculty, User.STUDENT);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public List<User> getAllStudentByCourseAndYearCode(String course, int yearCode) {
        return null;
    }

    @Override
    public User createUser(User u) {
        Long sequence = userRepo.findMaxSequenceNumByRoleAndFaculty(u.getRole(), u.getFaculty());
        if (sequence == null)
            sequence = 1L;
        else
            sequence++;
        Long facultyCode = u.getFaculty().getId();
        Long roleCode;
        switch (u.getRole()) {
            case User.ADMIN:
                roleCode = 1L;
                break;
            case User.STUDENT:
                roleCode = 2L;
                break;
            case User.TEACHER:
                roleCode = 3L;
                break;
            default:
                roleCode = 0L;
        }

        Long userId = roleCode * 1000000 + facultyCode * 10000 + sequence;
        u.setId(userId);
        u.setSequenceNum(sequence);

        this.accountService.newAccount(u);

        return this.userRepo.save(u);
    }

    @Override
    public void importUserFromCsv(String filePath) {
        try {
            Reader reader = new FileReader(filePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            for (CSVRecord csvRecord : csvParser) {
                String email = csvRecord.get("email");
                String lastName = csvRecord.get("lastName");
                String firstName = csvRecord.get("firstName");
                String dobStr = csvRecord.get("dob");
                Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
                String gender = csvRecord.get("gender");
                String role = csvRecord.get("role");
                String facultyId = csvRecord.get("faculty");
                Faculty faculty = facultyService.getById(Long.parseLong(facultyId));

                this.createUser(new User(email, firstName, lastName, dob, gender, role, faculty));
            }

            this.processUsersWithoutAccount();

            csvParser.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createAccountIfNotExists(User u) {
        if (u.getAccount() == null)
            this.accountService.newAccount(u);
    }

    @Override
    public void processUsersWithoutAccount() {
        List<User> users = this.userRepo.findAll();
        for (User user : users) {
            this.createAccountIfNotExists(user);
        }
    }

    @Override
    public User updateUser(Long id, User newUserData) {
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            User user = userRepo.findById(id).get();
            userRepo.delete(user);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
