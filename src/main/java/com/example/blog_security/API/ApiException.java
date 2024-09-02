package com.example.blog_security.API;

public class ApiException extends RuntimeException{

    public ApiException (String message){
        super(message); 
    }
}
