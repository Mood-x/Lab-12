package com.example.blog_security.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blog_security.API.ApiException;
import com.example.blog_security.Model.Blog;
import com.example.blog_security.Model.User;
import com.example.blog_security.Repository.AuthRepository;
import com.example.blog_security.Repository.BlogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogSerivce {

    private final BlogRepository blogRepository; 
    private final AuthRepository authRepository; 

    public List<Blog> getAllPosts(){
        return blogRepository.findAll(); 
    }

    public List<Blog> getMyPosts(Integer authId){
        User user = authRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 

        return blogRepository.findAllByUser(user); 
    }

    public Blog getPostById(Integer authId, Integer blogId){
        User user =  authRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 

        Blog blog = blogRepository.findBlogById(blogId)
            .orElseThrow(() -> new ApiException("POST NOT FOUND")); 

        if(!blog.getUser().getId().equals(authId)){
            throw new ApiException("SORRY YOU DON'T HAVE AUTHORITY"); 
        }

        return blog; 
    }

    public Blog getPostByTitle(Integer authId, String title){
        User user =  authRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 

        Blog blog = blogRepository.findByTitleِAndUserId(title, authId)
            .orElseThrow(() -> new ApiException("POST NOT FOUND")); 

        if(!blog.getUser().getId().equals(authId)){
            throw new ApiException("SORRY YOU DON'T HAVE AUTHORITY"); 
        }

        return blog; 
    }

    public void createPost(Integer authId, Blog post){
        User user =  authRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 

        post.setUser(user);
        blogRepository.save(post); 
    }

    public void updatePost(Integer authId, Integer postId, Blog post){
        User user =  authRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND"));
        
        Blog blog = blogRepository.findBlogById(postId)
            .orElseThrow(() -> new ApiException("POST NOT FOUND"));

        if(!blog.getUser().getId().equals(authId)){
            throw new ApiException("SORRY YOU DON'T HAVE AUTHORITY"); 
        }

        blog.setTitle(post.getTitle());
        blog.setBody(post.getBody());
        blog.setUser(user);

        blogRepository.save(blog); 
    }

    public void deletePost(Integer authId, Integer postId){
        User user =  authRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND"));
    
        Blog blog = blogRepository.findBlogById(postId)
            .orElseThrow(() -> new ApiException("POST NOT FOUND"));

        if(!blog.getUser().getId().equals(authId)){
            throw new ApiException("SORRY YOU DON'T HAVE AUTHORITY"); 
        }

        blogRepository.delete(blog);
    }
}
