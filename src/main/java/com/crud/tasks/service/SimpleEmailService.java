package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalDouble;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {
    private JavaMailSender mailSender;
    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail) {

        log.info("Starting email preparation...");

        try {
            mailSender.send(createMimeMessage(mail));
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage() + e);
        }
    }

//    private SimpleMailMessage createMailMessage(final Mail mail) {
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
//        Optional toCc =Optional.of(mail.getToCc());
//        if(toCc.isPresent()) {
//            mailMessage.setCc(mail.getToCc());
//        }
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMessage());
//
//        return mailMessage;
//    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage());
        };

    }
}
