package com.marketplace.backend.domain.dto.user;

import lombok.Data;

@Data
public class ResponseUserDto {

    private String name;
    private String email;
    private String phoneNumber;
    private Double rating;
    private String facultyName;
    private String role;

}
