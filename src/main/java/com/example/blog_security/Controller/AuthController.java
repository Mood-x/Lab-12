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
import com.example.blog_security.Model.User;
import com.example.blog_security.Service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService; 

    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(authService.getAllUsers()); 
    } 

    @GetMapping("/get-user-by-id")
    public ResponseEntity<User> getUserById(@RequestParam Integer userId){
        return ResponseEntity.ok(authService.getUserById(userId)); 
    }

    @GetMapping("/get-user-by-username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username){
        return ResponseEntity.ok(authService.getUserByUsername(username)); 
    }


    @PostMapping("/register")
    public ResponseEntity<ApiRespinse> register(@Valid @RequestBody User user){
        authService.register(user);
        return ResponseEntity.ok(new ApiRespinse("User added successfully")); 
    }

    @PutMapping("/update-user")
    public ResponseEntity<ApiRespinse> updateUser(@AuthenticationPrincipal User auth, @RequestParam Integer userId, User user){
        authService.updateUser(auth.getId(), userId, user);
        return ResponseEntity.ok(new ApiRespinse("User: " + user.getUsername() + "updated successfully")); 
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<ApiRespinse> deleteUser(@RequestParam Integer userId){
        authService.deleteUser(userId);
        return ResponseEntity.ok(new ApiRespinse("User deleted successfully")); 
    } 
}

