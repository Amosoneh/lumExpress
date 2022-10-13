package africa.semicolon.lumexpress.data.dtos.requests;

import lombok.*;

@Setter
@Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class LoginRequest {
    private String email;
    private String password;
}
