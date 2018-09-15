package mock.notify;

import java.util.Optional;

public class NotificationServiceImpl implements NotificationService {
    private EmailService emailService;
    private SMSService smsService;

    public NotificationServiceImpl(EmailService emailService, SMSService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    @Override
    public void sendNotification(Optional<String> email, Optional<String> phoneNumber, String notificationContent) {
        if (email.isPresent()) {
            emailService.sendEmail(email.get(), notificationContent);
        }
        if (phoneNumber.isPresent()) {
            smsService.sendSMS(email.get(), notificationContent);
        }
    }
}
