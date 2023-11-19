package org.example.Adapter;

public class RegistrationNotificationEmailAdapter implements EmailService{
    private final EmailServiceImpl legacyEmailService;

    public RegistrationNotificationEmailAdapter(EmailServiceImpl legacyEmailService) {
        this.legacyEmailService = legacyEmailService;
    }

    @Override
    public void sendEmail(String to) {
        String message = "Вы успешно зарегистрировались в нашей платформе";
        legacyEmailService.send(to, "Регистрация", message);
    }
}
