package com.linhv.scheduling.repository;

import com.linhv.scheduling.model.AdmissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionTypeRepository extends JpaRepository<AdmissionType, Long> {
}
