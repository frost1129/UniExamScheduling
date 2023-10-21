package com.linhv.scheduling.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SchedulingRequestDTO {
    private Integer yearCode;
    private String date;
    private Integer length;
    private Integer size;
    private double mutationRate;
}
