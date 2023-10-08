package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.Course;
import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.User;
import com.linhv.scheduling.repository.CourseRepository;
import com.linhv.scheduling.service.CourseService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepo;

    @Override
    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course getById(String id) {
        return courseRepo.findById(id).get();
    }

    @Override
    public Course newCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public void importCourseFromCsv(String filePath) {
        try {
            Reader reader = new FileReader(filePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            for (CSVRecord csvRecord : csvParser) {
                String id = csvRecord.get("id");
                String name = csvRecord.get("name");
                float credit = Float.parseFloat(csvRecord.get("credit"));
                this.newCourse(new Course(id, name, credit));
            }

            csvParser.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course updateCourse(String id, Course course) {
        return null;
    }

    @Override
    public boolean deleteCourse(String id) {
        try {
            Course course = this.getById(id);
            courseRepo.delete(course);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
