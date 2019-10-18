package com.api.services;

import com.api.dto.JwtAuthenticationResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationServiceInterface {
    public JwtAuthenticationResponseDTO authenticateUser(String usernameOrEmail, String password);
}
