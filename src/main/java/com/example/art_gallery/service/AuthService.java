package com.example.art_gallery.service;

import com.example.art_gallery.dto.AuthResponseDTO;
import com.example.art_gallery.dto.LoginRequestDTO;
import com.example.art_gallery.dto.RegisterRequestDTO;
<<<<<<< HEAD
import com.example.art_gallery.security.JwtUtil;
import com.example.art_gallery.model.User;
import com.example.art_gallery.repository.UserRepository;
=======
import com.example.art_gallery.model.Role;
import com.example.art_gallery.security.JwtUtil;
import com.example.art_gallery.model.User;
import com.example.art_gallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
>>>>>>> 0fdd7c1fd0a33e5804464faa592cd91ea66701f4
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${admin.email}")
    private String adminEmail;

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

        if (request.getEmail().equals(adminEmail)) {
            user.setRole(Role.ROLE_ADMIN);
        } else {
            user.setRole(Role.ROLE_USER);
        }

        // save to DB
        userRepository.save(user);

        // generate token and return
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
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
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponseDTO(token);
    }
}