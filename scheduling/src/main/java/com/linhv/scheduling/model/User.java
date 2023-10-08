package com.linhv.scheduling.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String STUDENT = "ROLE_STUDENT";
    public static final String TEACHER = "ROLE_TEACHER";

    public static final String MALE = "Nam";
    public static final String FEMALE = "Nữ";

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private String role;

    @Column(name = "sequence_num")
    private Long sequenceNum;

//    STUDENT ONLY
//    @Column(name = "year_admit")
//    private int yearAdmit;
//
//    @Column(name = "year_grad")
//    private int yearGrad;

//    TEACHER ONLY
//    @Column(name = "education")
//    private String education;

    @ManyToOne
    @JoinColumn(name = "faculty")
    private Faculty faculty;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    private Account account;

    public User(String email, String firstName, String lastName, Date dob, String gender, String role, Faculty faculty) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.role = role;
        this.faculty = faculty;
    }
}
