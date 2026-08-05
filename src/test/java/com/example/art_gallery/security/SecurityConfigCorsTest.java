package com.example.art_gallery.security;

import com.example.art_gallery.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class SecurityConfigCorsTest {

    @Test
    void allowsLocalViteOriginsForDevelopment() {
        SecurityConfig config = new SecurityConfig(mock(JwtFilter.class), mock(UserRepository.class));

        CorsConfigurationSource source = config.corsConfigurationSource();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("127.0.0.1");
        request.setServerPort(5173);
        request.setRequestURI("/api/v1/artworks");

        CorsConfiguration corsConfiguration = source.getCorsConfiguration(request);

        assertTrue(corsConfiguration.getAllowedOrigins().contains("http://127.0.0.1:5173"));
        assertTrue(corsConfiguration.getAllowedOrigins().contains("http://localhost:5173"));
    }
}
