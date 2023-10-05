package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "year_admit")
    private int yearAdmit;

    @Column(name = "year_grad")
    private int yearGrad;

    @ManyToOne
    @JoinColumn(name = "student_class")
    private StudentClass studentClass;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
