package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.User;
import com.linhv.scheduling.repository.UserRepository;
import com.linhv.scheduling.service.FacultyService;
import com.linhv.scheduling.service.UserService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FacultyService facultyService;

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

        return userRepo.save(u);
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

            csvParser.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
