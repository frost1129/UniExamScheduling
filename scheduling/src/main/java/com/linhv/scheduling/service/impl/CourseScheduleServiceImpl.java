package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.Course;
import com.linhv.scheduling.model.CourseSchedule;
import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.User;
import com.linhv.scheduling.repository.CourseRepository;
import com.linhv.scheduling.repository.CourseScheduleRepository;
import com.linhv.scheduling.service.CourseScheduleService;
import com.linhv.scheduling.service.CourseService;
import com.linhv.scheduling.service.FacultyService;
import com.linhv.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleService {

    @Autowired
    private CourseScheduleRepository scheduleRepo;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Override
    public CourseSchedule getById(String id) {
        return scheduleRepo.findById(id).get();
    }

    @Override
    public List<CourseSchedule> getAllByYearCode(int year) {
        return scheduleRepo.findByYearCode(year);
    }

    @Override
    public List<CourseSchedule> getAllByYearCodeAndFaculty(int year, Long facultyId) {
        Faculty faculty = facultyService.getById(facultyId);
        return scheduleRepo.findByYearCodeAndFaculty(year, faculty);
    }

    @Override
    public List<CourseSchedule> getAllByYearCodeAndTeacher(int year, Long teacherId) {
        User teacher = userService.getUserById(teacherId);
        return scheduleRepo.findByYearCodeAndTeacher(year, teacher);
    }

    @Override
    public List<CourseSchedule> getAllByYearCodeAndCourse(int year, String courseId) {
        Course course = courseService.getById(courseId);
        return scheduleRepo.findByYearCodeAndCourse(year, course);
    }

    @Override
    public CourseSchedule newSchedule(CourseSchedule cs) {
        return scheduleRepo.save(cs);
    }

    @Override
    public CourseSchedule updateSchedule(String id, CourseSchedule cs) {
        return null;
    }

    @Override
    public boolean deleteSchedule(String id) {
        try {
            CourseSchedule schedule = this.getById(id);
            scheduleRepo.delete(schedule);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
