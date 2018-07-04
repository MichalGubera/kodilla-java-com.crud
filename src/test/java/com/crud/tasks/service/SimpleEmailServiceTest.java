package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test Message");
//        Mail mail = new Mail("test@test.com", "test@test.com","Test", "Test Message");

        MimeMessagePreparator mimeMessagePreparator = simpleEmailService.createMimeMessage(mail);
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
////        mailMessage.setCc(mail.getToCc());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMessage());

        //When
//        simpleEmailService.send(mail);
        javaMailSender.send(mimeMessagePreparator);

        //Then
//        verify(javaMailSender, times(1)).send(mailMessage);
        verify(javaMailSender, times(1)).send(mimeMessagePreparator);
    }

}