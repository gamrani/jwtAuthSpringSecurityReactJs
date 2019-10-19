package com.api.services;

import com.api.dto.JwtAuthenticationResponseDTO;
import com.api.dto.SignUpRequestDTO;
import com.api.models.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationServiceInterface {
     JwtAuthenticationResponseDTO authenticateUser(String usernameOrEmail, String password);
     User registrateUser(SignUpRequestDTO signUpRequestDTO);

}
