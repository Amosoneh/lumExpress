package africa.semicolon.lumexpress.data.dtos.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateProductResponse {
    private Long productId;
    private String message;
    private int code;
}
