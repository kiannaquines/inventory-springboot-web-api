package com.example.inventory_api.auth.dto;

public record RegisterRequest(
    String username,
    String email,
    String password
) {}
