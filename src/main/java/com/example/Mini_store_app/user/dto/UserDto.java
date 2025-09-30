package com.example.Mini_store_app.user.dto;

import com.example.Mini_store_app.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
}
