package africa.semicolon.lumexpress.data.dtos.requests;

import lombok.*;

@Setter @Getter @AllArgsConstructor @Builder @NoArgsConstructor
public class EmailNotificationRequest {
    private String userEmail;
    private String mailContent;
}
