package africa.semicolon.lumexpress.data.dtos.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegisterResponse {
    private String message;
    private Long userId;
    private int code;
}
