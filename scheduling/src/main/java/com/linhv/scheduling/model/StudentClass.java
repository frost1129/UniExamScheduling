package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentClass {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @ManyToOne
    @JoinColumn(name = "class_year")
    private ClassYear classYear;

    @ManyToOne
    @JoinColumn(name = "faculty")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "admission_type")
    private AdmissionType admissionType;
}
