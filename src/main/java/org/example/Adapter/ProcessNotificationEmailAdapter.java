package org.example.Adapter;

public class ProcessNotificationEmailAdapter implements EmailService {
    private final EmailServiceImpl legacyEmailService;

    public ProcessNotificationEmailAdapter(EmailServiceImpl legacyEmailService) {
        this.legacyEmailService = legacyEmailService;
    }

    @Override
    public void sendEmail(String to) {
        String message = to + " sent process order notification";
        legacyEmailService.send(to, message);
    }
}
