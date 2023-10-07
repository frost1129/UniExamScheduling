package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "time_slot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "fromTime")
    @Temporal(TemporalType.TIME)
    private Date fromTime;
}
