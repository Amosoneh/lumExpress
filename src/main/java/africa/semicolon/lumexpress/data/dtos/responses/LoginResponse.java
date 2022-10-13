package africa.semicolon.lumexpress.data.dtos.responses;

import lombok.*;

@Setter @Getter @AllArgsConstructor @Builder @NoArgsConstructor
public class LoginResponse {
    private String message;
    private int code;
}
