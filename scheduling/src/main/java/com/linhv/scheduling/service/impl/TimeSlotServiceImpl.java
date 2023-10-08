package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.TimeSlot;
import com.linhv.scheduling.repository.TimeSlotRepository;
import com.linhv.scheduling.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    private TimeSlotRepository slotRepo;

    @Override
    public List<TimeSlot> getAll() {
        return slotRepo.findAll();
    }

    @Override
    public TimeSlot getById(Long id) {
        return slotRepo.findById(id).get();
    }

    @Override
    public TimeSlot newTimeSlot(TimeSlot slot) {
        return slotRepo.save(slot);
    }

    @Override
    public TimeSlot updateTimeSlot(TimeSlot slot) {
        return null;
    }

    @Override
    public boolean deleteTimeSlot(Long id) {
        try {
            TimeSlot slot = this.getById(id);
            slotRepo.delete(slot);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
