package com.example.inventory_api.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.inventory_api.auth.dto.AuthResponse;
import com.example.inventory_api.auth.dto.RegisterRequest;
import com.example.inventory_api.auth.entity.Role;
import com.example.inventory_api.auth.entity.User;
import com.example.inventory_api.auth.repository.UserRepository;
import com.example.inventory_api.security.JwtService;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplementationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void publicRegistrationAlwaysCreatesUserRole() {
        when(passwordEncoder.encode("password")).thenReturn("encoded-password");
        when(jwtService.generateToken(any(User.class))).thenReturn("token");

        AuthServiceImplementation authService = new AuthServiceImplementation(
                userRepository,
                passwordEncoder,
                jwtService,
                authenticationManager
        );

        AuthResponse response = authService.register(
                new RegisterRequest("new-user", "user@example.com", "password")
        );

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertThat(userCaptor.getValue().getRole()).isEqualTo(Role.USER);
        assertThat(response.role()).isEqualTo(Role.USER);
    }
}
