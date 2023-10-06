package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.repository.FacultyRepository;
import com.linhv.scheduling.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepo;

    @Override
    public Faculty getById(Long id) {
        return facultyRepo.getReferenceById(id);
    }
}
