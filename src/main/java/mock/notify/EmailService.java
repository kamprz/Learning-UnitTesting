package mock.notify;

public interface EmailService
{
    public EmailMessage sendEmail(String email, String content);

}
