package mock.notify;

import java.util.Optional;

public interface NotificationService
{
    public void sendNotification(Optional<String> email,
                                 Optional<String> phoneNumber,
                                 String notificationContent);
}