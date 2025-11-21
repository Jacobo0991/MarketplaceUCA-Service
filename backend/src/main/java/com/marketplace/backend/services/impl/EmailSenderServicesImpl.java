package com.marketplace.backend.services.impl;

import com.marketplace.backend.services.iEmailSenderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServicesImpl implements iEmailSenderServices {

    private final JavaMailSender mailSender;

    @Async
    @Override
    public void sendEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Codigo de verificación - Marketplace UCA");
        message.setText("Tu código de verificación es: " + otp + "\nEste código expira en 8 minutos");
        mailSender.send(message);
    }
}
