package africa.semicolon.lumexpress.services.notifications;

import africa.semicolon.lumexpress.data.dtos.requests.EmailNotificationRequest;

import javax.mail.MessagingException;

public interface EmailNotificationService {
    String sendHtmlMail(EmailNotificationRequest emailNotificationRequest) throws MessagingException;
}
