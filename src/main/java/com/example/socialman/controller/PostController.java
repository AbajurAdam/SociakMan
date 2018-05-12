package com.example.socialman.controller;

import com.example.socialman.Post;
import com.example.socialman.exception.ResourceNotFoundException;
import com.example.socialman.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @PostMapping("posts")
    public Post createPost(@Valid @RequestBody Post post){
        return postRepository.save(post);
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable(value = "id") Long postId){
        return postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
    }

    @PutMapping("/notes/{id}")
    public Post updatePost(@PathVariable(value = "id") Long postId,
                           @Valid @RequestBody Post postDetails){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));

        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());

        Post updatedPost = postRepository.save(post);
        return updatedPost;
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") Long postId){
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));

        postRepository.delete(post);
        return ResponseEntity.ok().build();
    }
}
