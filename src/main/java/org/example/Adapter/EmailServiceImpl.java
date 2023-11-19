package org.example.Adapter;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EmailServiceImpl {
    public void send(String email, String topic, String message) {
        System.out.println("Sending email to " + email + ": " + message);
        // Логика отправки сообщения
        String mail = "karataev020902@gmail.com";
        org.simplejavamail.api.mailer.Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, mail, "ykzepzbmaaiqbmck")
                .withDebugLogging(false)
                .buildMailer();

        Email emailTemplate = EmailBuilder.startingBlank()
                .to(email)
                .from(mail)
                .withSubject(topic)
                .withPlainText(message)
                .buildEmail();

        mailer.sendMail(emailTemplate);
    }
}
