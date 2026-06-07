package com.example.inventory_api.auth.dto;

import com.example.inventory_api.auth.entity.Role;

public record AuthResponse(
    String token,
    String username,
    String email,
    Role role
) {}
