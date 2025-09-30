package com.example.Mini_store_app.user.dto;


import com.example.Mini_store_app.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private UserRole userRole;
    private Long userId;
}
