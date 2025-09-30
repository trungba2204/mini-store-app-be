package com.example.Mini_store_app.service.auth;

import com.example.Mini_store_app.user.dto.LoginRequest;
import com.example.Mini_store_app.user.dto.UserDto;

public interface AuthService {
    UserDto createUser(LoginRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
