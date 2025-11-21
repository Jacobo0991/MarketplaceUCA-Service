package com.marketplace.backend.controllers;

import com.marketplace.backend.domain.dto.user.*;
import com.marketplace.backend.domain.dto.GeneralResponse;
import com.marketplace.backend.domain.entities.Token;
import com.marketplace.backend.domain.entities.User;
import com.marketplace.backend.services.iTokenServices;
import com.marketplace.backend.services.iUserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final iUserServices UserServices;
    private final iTokenServices TokenServices;
    private final AuthenticationManager authenticationManager;

    @PostMapping("user/auth/register")
    public ResponseEntity<GeneralResponse> register(@RequestBody @Valid PreRegisterUserDto userDto) {
        UserServices.preRegisterUser(userDto);


        return GeneralResponse.builder()
                .data("Sent to email: " + userDto.getEmail())
                .message("Email sent successfully!")
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("user/auth/verify")
    public ResponseEntity<GeneralResponse> verify(@RequestBody @Valid CreateUserDto userDto) {
        UserServices.registerUser(userDto);

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        Token token = TokenServices.generateToken((User) auth.getPrincipal());

        return GeneralResponse.builder()
                .data(token.getToken())
                .message("User created successfully!")
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("user/auth/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody AccessUserDto userDto) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        Token token = TokenServices.generateToken((User) auth.getPrincipal());

        return GeneralResponse.builder()
                .data(token.getToken())
                .message("Access granted")
                .status(HttpStatus.CREATED)
                .build();
    }

    @PatchMapping("admin/user/reassign")
    public ResponseEntity<GeneralResponse> reassign(@RequestParam("email") String email, @RequestParam("role") String role) {
        UserServices.reassignUserByUsername(email, role);

        return GeneralResponse.builder()
                .data(" ")
                .message("User reassigned successfully")
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("user/password")
    public ResponseEntity<GeneralResponse> resetPassword(@RequestBody @Valid ResetPasswordDto resetPasswordDto) {
        UserServices.changePassword(resetPasswordDto.getOldPassword(), resetPasswordDto.getNewPassword());

        return GeneralResponse.builder()
                .data(" ")
                .message("Password reset successfully")
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @PatchMapping("user/phoneNumber")
    public ResponseEntity<GeneralResponse> updatePhoneNumber(@RequestBody @Valid UpdateUserDto userDto) {
        UserServices.changePhoneNumber(userDto.getPhoneNumber());

        return GeneralResponse.builder()
                .data(" ")
                .message("Phone Number updated successfully")
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("user/all")
    public ResponseEntity<GeneralResponse> getAllUsers() {
        List<ResponseUserDto> response = UserServices.getAllUsers("USER");

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("admin/user/all")
    public ResponseEntity<GeneralResponse> getAllAdminUsers() {
        List<ResponseUserDto> response = UserServices.getAllUsers("ADMIN");

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("user/email")
    public ResponseEntity<GeneralResponse> getUserByEmail(@RequestParam("email") String email) {
        ResponseUserDto response = UserServices.getUserByEmail(email);

        return GeneralResponse.builder()
                .data(response)
                .message("ok")
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("user/name")
    public ResponseEntity<GeneralResponse> getUsersByNameContaining(@RequestParam("name") String name) {
        List<ResponseUserDto> response = UserServices.getUsersByNameContainingIgnoreCase(name);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("user/faculty")
    public ResponseEntity<GeneralResponse> getUsersByFaculty(@RequestParam("faculty") String facultyName) {
        List<ResponseUserDto> response = UserServices.getUsersByFaculty(facultyName);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("user/rating")
    public ResponseEntity<GeneralResponse> getUsersByRating(@RequestParam("rating") Integer rating) {
        List<ResponseUserDto> response = UserServices.getUsersByRating(rating);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }
}
