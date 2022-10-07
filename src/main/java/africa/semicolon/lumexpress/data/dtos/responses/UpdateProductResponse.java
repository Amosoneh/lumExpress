package africa.semicolon.lumexpress.data.dtos.responses;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @Builder @ToString @AllArgsConstructor @NoArgsConstructor
public class UpdateProductResponse {
    private String message;
    private int statusCode;
    private String productName;
    private String description;
    private BigDecimal price;
}
