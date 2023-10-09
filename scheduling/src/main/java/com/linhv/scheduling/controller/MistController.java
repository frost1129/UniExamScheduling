package com.linhv.scheduling.controller;

import com.linhv.scheduling.model.AdmissionType;
import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.TimeSlot;
import com.linhv.scheduling.service.AdmissionTypeService;
import com.linhv.scheduling.service.FacultyService;
import com.linhv.scheduling.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mist")
public class MistController {

    @Autowired
    private AdmissionTypeService typeService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private TimeSlotService slotService;

//    ADMISSION TYPE
    @GetMapping("/admissions/{typeId}")
    public ResponseEntity<AdmissionType> getAdmissionTypeById(@PathVariable(name = "typeId") Long id) {
        return new ResponseEntity<>(this.typeService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/admissions/")
    public ResponseEntity<List<AdmissionType>> getAllAdmissionType() {
        return new ResponseEntity<>(this.typeService.getAll(), HttpStatus.OK);
    }

//    FACULTY
    @GetMapping("/faculties/{facultyId}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable(name = "facultyId") Long id) {
        return new ResponseEntity<>(this.facultyService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/faculties/")
    public ResponseEntity<List<Faculty>> getAllFaculty() {
        return new ResponseEntity<>(this.facultyService.getAll(), HttpStatus.OK);
    }

//    TIMESLOT
    @GetMapping("/slots/{slotId}")
    public ResponseEntity<TimeSlot> getSlotById(@PathVariable(name = "slotId") Long id) {
        return new ResponseEntity<>(this.slotService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/slots/")
    public ResponseEntity<List<TimeSlot>> getAllSlot() {
        return new ResponseEntity<>(this.slotService.getAll(), HttpStatus.OK);
    }

}
