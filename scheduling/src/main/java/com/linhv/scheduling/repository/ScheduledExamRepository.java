package com.linhv.scheduling.repository;

import com.linhv.scheduling.model.ScheduledExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledExamRepository extends JpaRepository<ScheduledExam, Long> {
}
