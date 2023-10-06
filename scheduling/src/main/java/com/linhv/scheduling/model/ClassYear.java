package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "class_year")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year")
    private int year;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
