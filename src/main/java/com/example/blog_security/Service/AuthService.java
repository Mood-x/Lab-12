package com.example.blog_security.Service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog_security.API.ApiException;
import com.example.blog_security.Model.User;
import com.example.blog_security.Repository.AuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository; 

    public List<User> getAllUsers(){
        return authRepository.findAll(); 
    }

    public User getUserById(Integer userId){
        User user = authRepository.findUserById(userId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 
        
        return user; 
    }

    public User getUserByUsername(String username){
        User user = authRepository.findUserByUsername(username)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 
        
        return user; 
    }

    public void register(User user){
        user.setRole("USER"); 
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(hash);
        authRepository.save(user); 
    }

    public void updateUser(Integer authId, Integer userId, User updateUser){
        User user = authRepository.findUserById(userId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 

        if(user.getId().equals(authId)){
            user.setUsername(updateUser.getUsername());
            user.setPassword(updateUser.getPassword());
    
            authRepository.save(user); 
        }
        
        throw new ApiException("SORRY YOU DON'T HAVE AUTHORITY"); 
    }

    public void deleteUser(Integer userId){
        User user = authRepository.findUserById(userId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 

        authRepository.delete(user);
    }


}
