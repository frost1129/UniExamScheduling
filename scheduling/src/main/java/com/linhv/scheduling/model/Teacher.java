package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "education")
    private String education;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
