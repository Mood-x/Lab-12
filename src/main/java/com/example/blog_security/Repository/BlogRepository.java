package com.example.blog_security.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.blog_security.Model.Blog;
import com.example.blog_security.Model.User;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{

    Optional<Blog> findBlogById(Integer id); 

    @Query("select b from Blog b where b.title = :title and b.user.id = :userId")
    Optional<Blog> findByTitleِAndUserId(@Param("title") String title, @Param("userId") Integer userId);
    List<Blog> findAllByUser(User user); 
}
