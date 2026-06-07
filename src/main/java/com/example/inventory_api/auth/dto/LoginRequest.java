package com.example.inventory_api.auth.dto;

public record LoginRequest(
    String username,
    String password
) {}
