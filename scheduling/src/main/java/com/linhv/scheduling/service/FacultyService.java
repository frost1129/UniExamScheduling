package com.linhv.scheduling.service;

import com.linhv.scheduling.model.Faculty;

import java.util.List;

public interface FacultyService {

//    READ
    Faculty getById(Long id);
    List<Faculty> getAll();

//    CREATE
    Faculty newFaculty(Faculty faculty);

//    UPDATE
    Faculty updateFaculty(Long id, Faculty faculty);

//    DELETE
    boolean deleteFaculty(Long id);
}
