package com.linhv.scheduling.model.dto;

import com.linhv.scheduling.model.Faculty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOFacultyRole {
    private Faculty faculty;
    private String role;
}
