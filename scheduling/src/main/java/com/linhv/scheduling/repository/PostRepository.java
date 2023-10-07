package com.linhv.scheduling.repository;

import com.linhv.scheduling.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findTop5();
    List<Post> findByAdmin_Id(Long adminId);
}
