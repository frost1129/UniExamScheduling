package com.linhv.scheduling.repository;

import com.linhv.scheduling.model.ClassYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassYearRepository extends JpaRepository<ClassYear, Integer> {
}
