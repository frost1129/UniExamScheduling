package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.AdmissionType;
import com.linhv.scheduling.repository.AdmissionTypeRepository;
import com.linhv.scheduling.service.AdmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdmissionTypeServiceImpl implements AdmissionTypeService {

    @Autowired
    AdmissionTypeRepository typeRepo;

    @Override
    public AdmissionType getById(Long id) {
        return typeRepo.findById(id).get();
    }

    @Override
    public List<AdmissionType> getAll() {
        return typeRepo.findAll();
    }

    @Override
    public AdmissionType newType(AdmissionType admissionType) {
        return typeRepo.save(admissionType);
    }

    @Override
    public AdmissionType updateType(Long id, AdmissionType admissionType) {
        return null;
    }

    @Override
    public boolean deleteType(Long id) {
        try {
            AdmissionType type = this.getById(id);
            typeRepo.delete(type);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
