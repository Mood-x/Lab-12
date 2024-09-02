package com.example.blog_security.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_security.Model.Blog;
import com.example.blog_security.Model.User;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{

    Optional<Blog> findBlogById(Integer id); 
    Optional<Blog> findBlogByTitle(String title);
    List<Blog> findAllByUser(User user); 
}
