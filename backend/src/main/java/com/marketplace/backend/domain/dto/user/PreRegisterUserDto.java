package com.marketplace.backend.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PreRegisterUserDto {
    @JsonProperty("email")
    @NotBlank
    @NotNull
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@uca\\.edu\\.sv$")
    private String email;
}
