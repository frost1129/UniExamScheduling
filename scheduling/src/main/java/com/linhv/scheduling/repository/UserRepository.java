package com.linhv.scheduling.repository;

import com.linhv.scheduling.model.Faculty;
import com.linhv.scheduling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT MAX(u.sequenceNum) FROM User u WHERE u.role = :role AND u.faculty = :faculty")
    Long findMaxSequenceNumByRoleAndFaculty(@Param("role") String role, @Param("faculty") Faculty faculty);
}
