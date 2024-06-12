package com.example.demo.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void send(String emailTo, String subject, String message){
        System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("gapmeeee@yandex.ru");
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
