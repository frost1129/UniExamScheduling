package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.CourseSchedule;
import com.linhv.scheduling.model.StudentJoinCourse;
import com.linhv.scheduling.model.User;
import com.linhv.scheduling.repository.StudentJoinCourseRepository;
import com.linhv.scheduling.service.CourseScheduleService;
import com.linhv.scheduling.service.StudentJoinCourseService;
import com.linhv.scheduling.service.UserService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudentJoinCourseServiceImpl implements StudentJoinCourseService {

    @Autowired
    private StudentJoinCourseRepository joinRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseScheduleService scheduleService;

    @Override
    public StudentJoinCourse getById(Long id) {
        return joinRepo.findById(id).get();
    }

    @Override
    public List<StudentJoinCourse> getAllByStudent(Long studentId) {
        return joinRepo.findByStudent_Id(studentId);
    }

    @Override
    public List<StudentJoinCourse> getAllByCourse(String courseId) {
        return joinRepo.findByCourseSchedule_ScheduleId(courseId);
    }

    @Override
    public List<User> getAllUserByCourse(String courseId) {
        List<StudentJoinCourse> joins = this.getAllByCourse(courseId);
        return joins.stream()
                .map(StudentJoinCourse::getStudent)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseSchedule> getAllScheduleByStudentAndYearCode(User student, int yearCode) {
        return this.joinRepo.findCourseSchedulesByStudentAndYearCode(student, yearCode);
    }

    @Override
    public StudentJoinCourse newJoin(StudentJoinCourse join) {
        return joinRepo.save(join);
    }

    @Override
    public void importDataFromCsv(MultipartFile file, int yearCode) throws IOException, ParseException {
        if (!file.isEmpty()) {
            // Chuyển MultipartFile thành InputStream
            InputStream inputStream = file.getInputStream();

            // Đọc dữ liệu từ InputStream bằng OpenCSV
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            for (CSVRecord csvRecord : csvParser) {
//                System.out.println(csvRecord.get(1));
                StudentJoinCourse register = new StudentJoinCourse();

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                register.setRegisterDate(formatter.parse(csvRecord.get("registerDate")));

                register.setStudent(this.userService.getUserById(Long.parseLong(csvRecord.get("studentId"))));
                register.setCourseSchedule(this.scheduleService.getById(String.format("%s-%d", csvRecord.get("scheduleId"), yearCode)));

                this.joinRepo.save(register);
            }
            csvParser.close();
            reader.close();
        }
    }

    @Override
    public StudentJoinCourse updateJoin(Long sjcId, StudentJoinCourse join) {
        return null;
    }

    @Override
    public boolean deleteJoin(Long id) {
        try {
            StudentJoinCourse join = this.getById(id);
            joinRepo.delete(join);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
