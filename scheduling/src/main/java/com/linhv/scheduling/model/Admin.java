package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
