package com.example.blog_security.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_security.API.ApiRespinse;
import com.example.blog_security.Model.Blog;
import com.example.blog_security.Model.User;
import com.example.blog_security.Service.BlogSerivce;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogSerivce blogSerivce; 

    @GetMapping("/get-all-posts")
    public ResponseEntity<List<Blog>> getAllPosts(){
        return ResponseEntity.ok(blogSerivce.getAllPosts()); 
    }

    @GetMapping("/get-my-posts")
    public ResponseEntity<List<Blog>> getMyPosts(Integer authId){
        return ResponseEntity.ok(blogSerivce.getMyPosts(authId));
    }

    @GetMapping("/get-post-by-id")
    public ResponseEntity<Blog> getPostById(Integer authId, @RequestParam Integer postId){
        return ResponseEntity.ok(blogSerivce.getPostById(authId, postId));
    }

    @GetMapping("/get-post-by-title")
    public ResponseEntity<Blog> getPostByTitle(Integer authId, @RequestParam String title){
        return ResponseEntity.ok(blogSerivce.getPostByTitle(authId, title));
    }

    @PostMapping("/create-post")
    public ResponseEntity<ApiRespinse> createPost(@AuthenticationPrincipal User user, @Valid @RequestBody Blog post){
        blogSerivce.createPost(user.getId(), post);
        return ResponseEntity.ok(new ApiRespinse("Post added successfully")); 
    }

    @PutMapping("/edit-post")
    public ResponseEntity<ApiRespinse> updatePost(@AuthenticationPrincipal User user, @RequestParam Integer postId, @Valid @RequestBody Blog post){
        blogSerivce.updatePost(user.getId(), postId, post);
        return ResponseEntity.ok(new ApiRespinse("Post updated successfully")); 
    }

    @DeleteMapping("/delete-post")
    public ResponseEntity<ApiRespinse> deletePost(@AuthenticationPrincipal User user, @RequestParam Integer postId){
        blogSerivce.deletePost(user.getId(), postId);
        return ResponseEntity.ok(new ApiRespinse("Post deleted successfully")); 
    }
}
