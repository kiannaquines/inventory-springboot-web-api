package com.example.inventory_api.auth.service;

import com.example.inventory_api.auth.dto.AuthResponse;
import com.example.inventory_api.auth.dto.LoginRequest;
import com.example.inventory_api.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
