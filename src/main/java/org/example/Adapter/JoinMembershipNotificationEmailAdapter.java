package org.example.Adapter;

public class JoinMembershipNotificationEmailAdapter implements EmailService {
    private final EmailServiceImpl legacyEmailService;

    public JoinMembershipNotificationEmailAdapter(EmailServiceImpl legacyEmailService) {
        this.legacyEmailService = legacyEmailService;
    }

    @Override
    public void sendEmail(String to) {
        String message = "Вы успешно приобрели абонемент";
        legacyEmailService.send(to, "Приобретение абонемента",message);
    }
}
