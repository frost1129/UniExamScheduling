package com.linhv.scheduling.service;

import com.linhv.scheduling.model.AdmissionType;

import java.util.List;

public interface AdmissionTypeService {

//    READ
    AdmissionType getById(Long id);
    List<AdmissionType> getAll();

//    CREATE
    AdmissionType newType(AdmissionType admissionType);

//    UPDATE
    AdmissionType updateType(Long id, AdmissionType admissionType);

//    DELETE
    boolean deleteType(Long id);
}
