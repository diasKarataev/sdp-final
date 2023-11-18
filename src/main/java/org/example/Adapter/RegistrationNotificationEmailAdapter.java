package org.example.Adapter;

public class RegistrationNotificationEmailAdapter implements EmailService{
    private final EmailServiceImpl legacyEmailService;

    public RegistrationNotificationEmailAdapter(EmailServiceImpl legacyEmailService) {
        this.legacyEmailService = legacyEmailService;
    }

    @Override
    public void sendEmail(String to) {
        String message = to + " sent registration mail";
        legacyEmailService.send(to, message);
    }
}
