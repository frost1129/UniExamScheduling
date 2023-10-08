package com.linhv.scheduling.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    public static final String DEFAULT_PASSWORD = "123456";
    public static final String DEFAULT_IMAGE = "https://res.cloudinary.com/dbh8vdpi7/image/upload/v1696798063/user-profile-interface-sign-symbol-icon-3d-rendering_rkjccs.jpg";

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Transient
    private String confPassword;

    @Column(name = "role")
    private String role;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Transient
    private MultipartFile imageFile;

    @Column(name = "status")
    private String status;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private User user;
}
