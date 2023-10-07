package com.linhv.scheduling.service;

import com.linhv.scheduling.model.TimeSlot;

import java.util.List;

public interface TimeSlotService {

//    READ
    List<TimeSlot> getAll();
    TimeSlot getById(Long id);

//    CREATE
    TimeSlot newTimeSlot(TimeSlot slot);

//    UPDATE
    TimeSlot updateTimeSlot(TimeSlot slot);

//    DELETE
    boolean deleteTimeSlot(Long id);
}
