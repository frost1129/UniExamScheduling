package com.linhv.scheduling.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Transient
    private MultipartFile imageFile;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToOne
    @JoinColumn(name = "admission_type")
    private AdmissionType admissionType;
}
