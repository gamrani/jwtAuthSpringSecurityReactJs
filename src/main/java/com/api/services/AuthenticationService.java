package com.api.services;

import com.api.dto.JwtAuthenticationResponseDTO;
import com.api.dto.SignUpRequestDTO;
import com.api.exceptions.AlreadyExistsException;
import com.api.exceptions.AppException;
import com.api.exceptions.NotFoundException;
import com.api.mappers.UserMappers;
import com.api.models.Role;
import com.api.models.User;
import com.api.repositories.RoleRepository;
import com.api.repositories.UserRepository;
import com.api.security.utils.JwtTokenProvider;
import com.api.type.RoleName;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class AuthenticationService implements AuthenticationServiceInterface {
    public static final Logger logger = Logger.getLogger(AuthenticationService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public JwtAuthenticationResponseDTO authenticateUser(String usernameOrEmail, String password) {
        logger.info("authenticate user :"+usernameOrEmail);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usernameOrEmail,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponseDTO(jwt);
    }

    private boolean userExists(User user){

        return userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail()) ;
    }

    @Override
    public User registrateUser(SignUpRequestDTO signUpRequestDTO) {
        User user = UserMappers.mappSignUpRequestDtoToUser(signUpRequestDTO);

        if(userExists(user)){
            logger.error("User already exists");
            throw new AlreadyExistsException("User already exists !");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow( () -> new NotFoundException("Role user doesn't exist"));
        user.setRoles(Collections.singleton(userRole));

        User userSaved = userRepository.save(user);

        logger.info("Persist user to database with id  : "+userSaved.getId());
        return userSaved;
    }
}
