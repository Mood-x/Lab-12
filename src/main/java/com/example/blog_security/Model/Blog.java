package com.example.blog_security.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @NotEmpty(message = "Title should be not empty")
    @Size(min = 4, message = "Minimum title length must be 4 chracters")
    @Column(columnDefinition = "varchar(40) not null")
    private String title; 

    @NotEmpty(message = "Body should be not empty")
    @Size(min = 10, message = "Minimum body length must be 10 chracters")
    @Column(columnDefinition = "text not null")
    private String body; 


    @ManyToOne
    @JsonIgnore
    private User user; 
}
