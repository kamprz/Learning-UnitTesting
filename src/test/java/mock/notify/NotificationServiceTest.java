package mock.notify;

import static org.junit.Assert.*;

import mock.notify.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Iterator;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest
{
    @Mock
    EmailService emailService;
    @Mock
    SMSService smsService;

    NotificationService notificationService;
    @Before
    public void setUp()
    {
        notificationService = new NotificationServiceImpl(emailService,smsService);
    }

    @Test
    public void smsTest()
    {
        //given
        SMSMessage smsMessage = new SMSMessage("SMS");
        Mockito.when(smsService.sendSMS(Matchers.anyObject(),Matchers.anyObject())).thenReturn(smsMessage);
        //when
        String sms = smsService.sendSMS("123456789","SMSContent").getMessage();
        //then
        assertEquals("SMS",sms);
    }

    @Test
    public void emailTest()
    {
        //given
        EmailMessage message = new EmailMessage("email");
        Mockito.when(emailService.sendEmail(Matchers.anyObject(),Matchers.anyObject())).thenReturn(message);
        //when
        String email = emailService.sendEmail("mail@mail.com","content").getMessage();
        //then
        assertEquals("email",email);
    }

    @Test
    public void emailAdminTest()
    {
        //given
        EmailMessage message = new EmailMessage("email");
        EmailMessage adminMessage = new EmailMessage("admin");
        Mockito.when(emailService.sendEmail(Matchers.anyObject(),Matchers.anyObject())).thenReturn(message);
        Mockito.when(emailService.sendEmail(Matchers.matches("admin@.+\\..+"),Matchers.anyObject())).thenReturn(adminMessage);
        //when
        String email = emailService.sendEmail("anyMail@xd.pl","content").getMessage();
        String admin = emailService.sendEmail("admin@xd.pl","content").getMessage();
        //then
        assertEquals("email",email);
        assertEquals("admin",admin);
    }

    @Test
    public void mockIteratorTestWithoutWhen()
    {
        //given
        Iterator iterator = Mockito.mock(Iterator.class);
        //when
        Object next = iterator.next();
        //then
        assertNull(next);
    }

    @Test
    public void mockIteratorTestWithWhen()
    {
        //given
        Iterator iterator = Mockito.mock(Iterator.class);
        Mockito.when(iterator.next()).thenReturn("next");
        //when
        String next = (String)iterator.next();
        //then
        assertEquals("next",next);
    }
}
