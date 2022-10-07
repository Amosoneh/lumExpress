package africa.semicolon.lumexpress.services.notifications;

import africa.semicolon.lumexpress.data.dtos.requests.NotificationRequest;

public interface NotificationService {
    String send(NotificationRequest request);
}
