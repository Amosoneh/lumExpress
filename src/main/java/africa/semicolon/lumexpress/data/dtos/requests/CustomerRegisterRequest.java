package africa.semicolon.lumexpress.data.dtos.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegisterRequest {
    private String firstName;
    private String lastName;
    private String country;
    private String email;
    private String password;
    private int code;
}
