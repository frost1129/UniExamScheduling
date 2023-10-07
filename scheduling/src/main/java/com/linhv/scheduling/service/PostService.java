package com.linhv.scheduling.service;

import com.linhv.scheduling.model.Post;

import java.util.List;
import java.util.Map;

public interface PostService {

//    READ
    Post getById(String id);
    List<Post> getTop5();
    List<Post> getAll(Map<String, String> params);
    List<Post> getAllByUserId(Long id);
    Long countAll(Map<String, String> params);

//    CREATE
    Post newPost(Post post);

//    UPDATE
    Post updatePost(String id, Post post);

//    DELETE
    boolean deletePost(String id);
}
