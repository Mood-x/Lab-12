package com.example.blog_security.Model;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    @NotEmpty(message = "Username should be not empty")
    @Size(min = 3, max = 25, message = "Username must be between 3 to 25 characters")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username; 

    @NotEmpty(message = "Password should be not empty")
    @Size(min = 6, max = 256)
    @Column(columnDefinition = "varchar(256) not null")
    private String password; 

    @NotEmpty
    @Column(columnDefinition = "enum('USER', 'ADMIN') default 'USER' not null")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Blog> blogs; 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role)); 
    }

    @Override
    public boolean isAccountNonExpired(){
        return true; 
    }

    @Override
    public boolean isAccountNonLocked(){
        return true; 
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true; 
    }

    @Override
    public boolean isEnabled(){
        return true; 
    }
}
