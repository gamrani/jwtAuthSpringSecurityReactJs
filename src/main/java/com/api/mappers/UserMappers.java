package com.api.mappers;

import com.api.dto.SignUpRequestDTO;
import com.api.dto.SignUpResponseDto;
import com.api.models.User;

public class UserMappers {

    public static User mappSignUpRequestDtoToUser(SignUpRequestDTO signUpRequestDTO){
        User user = new User();
        user.setEmail(signUpRequestDTO.getEmail());
        user.setUsername(signUpRequestDTO.getUsername());
        user.setPassword(signUpRequestDTO.getPassword());
        user.setName(signUpRequestDTO.getName());

        return user;
    }

    public static SignUpResponseDto mapUserToSignUpResponseDto(User user){
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setEmail(user.getEmail());
        signUpResponseDto.setId(user.getId());
        signUpResponseDto.setName(user.getName());
        signUpResponseDto.setRoles(user.getRoles());
        signUpResponseDto.setUsername(user.getUsername());

        return signUpResponseDto;
    }
}
