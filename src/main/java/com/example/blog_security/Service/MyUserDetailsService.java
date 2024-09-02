package com.example.blog_security.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blog_security.API.ApiException;
import com.example.blog_security.Model.User;
import com.example.blog_security.Repository.AuthRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService{

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findUserByUsername(username)
            .orElseThrow(() -> new ApiException("WRONG USERNAME OR PASSWORD")); 
        
        return user; 
    } 
}
