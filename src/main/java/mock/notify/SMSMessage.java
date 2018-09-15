package mock.notify;

public class SMSMessage
{
    private String message;

    public SMSMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
