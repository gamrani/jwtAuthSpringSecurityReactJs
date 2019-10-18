package com.api.controllers;

import com.api.dto.LoginRequestDTO;
import com.api.dto.SignUpRequestDTO;
import com.api.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequestDTO loginRequestDTO){

        return ResponseEntity.ok(authenticationService.authenticateUser(
                loginRequestDTO.getUsernameOrEmail(),
                loginRequestDTO.getPassword()));

    }

    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO){
        return null;
    }
}
