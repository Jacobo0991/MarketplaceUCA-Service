package com.marketplace.backend.services.impl;

import com.marketplace.backend.domain.dto.user.CreateUserDto;
import com.marketplace.backend.services.iOtpServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class OtpServicesImpl implements iOtpServices {

    private final StringRedisTemplate redisTemplate;

    @Override
    public String generateOtp() {
        SecureRandom random = new SecureRandom();

        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public void saveOtp(String email, String otp) {
        redisTemplate.opsForValue().set(email, otp, Duration.ofMinutes(8));
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        String storedOtp = redisTemplate.opsForValue().get(email);
        return  otp.equals(storedOtp);
    }

    @Override
    public void deleteOtp(String email) {
        redisTemplate.delete(email);
    }
}
