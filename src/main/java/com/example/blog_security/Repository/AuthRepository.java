package com.example.blog_security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_security.Model.User;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer>{
    Optional<User> findUserById(Integer id);
    Optional<User> findUserByUsername(String username);
}
