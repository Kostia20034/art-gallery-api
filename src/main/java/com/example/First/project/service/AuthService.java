package com.example.First.project.service;

import com.example.First.project.dto.AuthResponseDTO;
import com.example.First.project.dto.LoginRequestDTO;
import com.example.First.project.dto.RegisterRequestDTO;
import com.example.First.project.exceptions.ProductNotFoundException;
import com.example.First.project.model.User;
import com.example.First.project.repository.UserRepository;
import com.example.First.project.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        // check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // create new user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // save to DB
        userRepository.save(user);

        // generate token and return
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        // find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // check password matches
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // generate token and return
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDTO(token);
    }
}