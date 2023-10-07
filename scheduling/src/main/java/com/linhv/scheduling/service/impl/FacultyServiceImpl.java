package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.Course;
import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.repository.FacultyRepository;
import com.linhv.scheduling.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepo;

    @Override
    public Faculty getById(Long id) {
        return facultyRepo.findById(id).get();
    }

    @Override
    public List<Faculty> getAll() {
        return facultyRepo.findAll();
    }

    @Override
    public Faculty newFaculty(Faculty faculty) {
        return facultyRepo.save(faculty);
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        return null;
    }

    @Override
    public boolean deleteFaculty(Long id) {
        try {
            Faculty faculty = this.getById(id);
            facultyRepo.delete(faculty);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
