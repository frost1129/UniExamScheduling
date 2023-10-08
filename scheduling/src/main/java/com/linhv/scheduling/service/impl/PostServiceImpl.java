package com.linhv.scheduling.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.github.slugify.Slugify;
import com.linhv.scheduling.model.Post;
import com.linhv.scheduling.repository.PostRepository;
import com.linhv.scheduling.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private Cloudinary cloudinary;

    private Slugify slg = Slugify.builder().build();

    @Override
    public Post getById(String id) {
        return postRepo.findById(id).get();
    }

    @Override
    public List<Post> getTop5() {
        return postRepo.findTop5ByOrderByUpdatedDateDesc();
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
//        slugify post id
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String formattedDate = dateFormat.format(new Date());
        post.setId(slg.slugify(post.getTitle()) + "-" + formattedDate);

//        set post data
        post.setUpdatedDate(new Date());
        uploadImage(post, post);

        return postRepo.save(post);
    }

    @Override
    public Post updatePost(String id, Post post) {
        try {
            Post curPost = this.getById(id);

            curPost.setContent(post.getContent());
            curPost.setUpdatedDate(new Date());
            curPost.setTitle(post.getTitle());
            uploadImage(post, curPost);

            return this.postRepo.save(curPost);
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    private void uploadImage(Post post, Post toUpdatePost) {
        if (post.getImageFile() != null && !post.getImageFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(post.getImageFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                toUpdatePost.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
