package com.marketplace.backend.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateUserDto {

    @JsonProperty("name")
    @NotNull
    @NotBlank
    private String name;

    @JsonProperty("email")
    @NotNull
    @NotBlank
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@uca\\.edu\\.sv$")
    private String email;

    @JsonProperty("password")
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{8,}$")
    private String password;

    @JsonProperty("faculty")
    @NotNull
    @NotBlank
    private String faculty;

    @JsonProperty("phoneNumber")
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[267]\\d{7}$")
    private String phoneNumber;

    @JsonProperty("otp")
    @NotNull
    @NotBlank
    private String otpCode;
}