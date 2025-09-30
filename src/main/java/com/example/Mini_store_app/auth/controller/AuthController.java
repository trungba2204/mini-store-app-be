package com.example.Mini_store_app.auth.controller;

import com.example.Mini_store_app.auth.authEntiy.AuthResponse;
import com.example.Mini_store_app.service.UserService;
import com.example.Mini_store_app.service.auth.AuthService;
import com.example.Mini_store_app.user.User;
import com.example.Mini_store_app.user.UserRepository;
import com.example.Mini_store_app.user.dto.AuthenticationRequest;
import com.example.Mini_store_app.user.dto.AuthenticationResponse;
import com.example.Mini_store_app.user.dto.LoginRequest;
import com.example.Mini_store_app.user.dto.UserDto;
import com.example.Mini_store_app.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody LoginRequest signupRequest){
        if (authService.hasCustomerWithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("exists!", HttpStatus.NOT_ACCEPTABLE);
        UserDto createdUserDto = authService.createUser(signupRequest);
        if (createdUserDto == null) return new  ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException, DisabledException, UsernameNotFoundException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorect");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }
        return authenticationResponse;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        // authentication được Spring Security inject dựa trên JWT đã verify
        String email = authentication.getName(); // chính là email đăng nhập

        Optional<User> optionalUser = userRepository.findFirstByEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = optionalUser.get();

        // Tạo response DTO gọn gàng
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("email", user.getEmail());
        response.put("role", user.getUserRole());
        response.put("fullName", user.getName());

        return ResponseEntity.ok(response);
    }

}
