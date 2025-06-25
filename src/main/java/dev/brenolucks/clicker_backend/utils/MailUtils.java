package dev.brenolucks.clicker_backend.utils;

import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MailUtils {
    private final JavaMailSender emailSender;

    public MailUtils(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String body, File image) throws MessagingException {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            helper.addAttachment(image.getName(), image);
        };

        emailSender.send(mimeMessagePreparator);
    }
}
