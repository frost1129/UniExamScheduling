package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

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

    public boolean isRightAfter(TimeSlot slot2) {
        return Objects.equals(this.id, slot2.getId() + 1L) || Objects.equals(this.id + 1L, slot2.getId());
    }
}
