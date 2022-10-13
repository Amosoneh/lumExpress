package africa.semicolon.lumexpress.services.notifications;

import africa.semicolon.lumexpress.data.dtos.requests.EmailNotificationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmailNotificationServiceImpTest {
    @Autowired
    private EmailNotificationService emailNotificationService;

    @Test
    void sentHtmlMailTest() throws MessagingException {
        EmailNotificationRequest emailNotificationRequest = new EmailNotificationRequest();
        emailNotificationRequest.setUserEmail("amoskhaled@gmail.com");
        emailNotificationRequest.setMailContent("Hello Mr. Amos!!");
        String response = emailNotificationService.sendHtmlMail(emailNotificationRequest);
        assertThat(response.contains("successfully")).isTrue();
    }
}