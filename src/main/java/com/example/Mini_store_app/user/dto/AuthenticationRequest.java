package com.example.Mini_store_app.user.dto;


import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
