package com.marketplace.backend.services;

import com.marketplace.backend.domain.dto.user.CreateUserDto;
import com.marketplace.backend.domain.dto.user.PreRegisterUserDto;
import com.marketplace.backend.domain.dto.user.ResponseUserDto;
import com.marketplace.backend.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface iUserServices extends UserDetailsService {

    void preRegisterUser(PreRegisterUserDto user);
    void registerUser(CreateUserDto user);
    ResponseUserDto getUserByUsername(String username);
    void reassignUserByUsername(String username, String role);
    void changePassword(String oldPassword, String newPassword);
    void changePhoneNumber(String newPhoneNumber);
    User getUserSession();
    List<ResponseUserDto> getAllUsers(String role);
    ResponseUserDto getUserByEmail(String email);
    List<ResponseUserDto> getUsersByNameContainingIgnoreCase(String name);
    List<ResponseUserDto> getUsersByFaculty(String facultyName);
    List<ResponseUserDto> getUsersByRating(Integer rating);
}
