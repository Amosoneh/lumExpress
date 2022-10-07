package africa.semicolon.lumexpress.data.dtos.requests;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class UpdateProductRequest {
    private BigDecimal price;
    private int quantity;
    private String description;
    private Long productId;
}
