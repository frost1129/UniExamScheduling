package com.linhv.scheduling.service.impl;

import com.linhv.scheduling.model.Post;
import com.linhv.scheduling.repository.PostRepository;
import com.linhv.scheduling.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepo;

    @Override
    public Post getById(String id) {
        return postRepo.findById(id).get();
    }

    @Override
    public List<Post> getTop5() {
        return postRepo.findTop5();
    }

    @Override
    public List<Post> getAll(Map<String, String> params) {
        return null;
    }

    @Override
    public List<Post> getAllByUserId(Long id) {
        return postRepo.findByAdmin_Id(id);
    }

    @Override
    public Long countAll(Map<String, String> params) {
        return null;
    }

    @Override
    public Post newPost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public Post updatePost(String id, Post post) {
        return null;
    }

    @Override
    public boolean deletePost(String id) {
        try {
            Post post = this.getById(id);
            postRepo.delete(post);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
