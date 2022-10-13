package africa.semicolon.lumexpress.data.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
@Entity @Builder
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    private Long id;
    private String token;
    private String userEmail;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
