package com.linhv.scheduling.controller;

import com.linhv.scheduling.model.Post;
import com.linhv.scheduling.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.postService.getAll(params), HttpStatus.OK);
    }

    @GetMapping("/top-5/")
    public ResponseEntity<List<Post>> getTop5Post() {
        return new ResponseEntity<>(postService.getTop5(), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "postId") String id) {
        return new ResponseEntity<>(this.postService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createPost(@ModelAttribute Post post) {
        this.postService.newPost(post);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(value = "postId") String id) {
        this.postService.deletePost(id);
        return new ResponseEntity<>("deleted", HttpStatus.NO_CONTENT);
    }
}
