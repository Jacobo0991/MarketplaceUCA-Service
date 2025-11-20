package com.marketplace.backend.services;

import com.marketplace.backend.domain.dto.user.CreateUserDto;

public interface iOtpServices {

    public String generateOtp();
    public void saveOtp(String email, String otp);
    public boolean verifyOtp(String email, String otp);
    public void deleteOtp(String email);
}
