package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    public static final String ACTIVE = "ACTIVE";
    public static final String LOCK = "LOCK";

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Transient
    private String confPassword;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Transient
    private MultipartFile imageFile;

    @Column(name = "status")
    private String status;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
