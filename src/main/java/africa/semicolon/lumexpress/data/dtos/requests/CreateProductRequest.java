package africa.semicolon.lumexpress.data.dtos.requests;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter @Builder
public class CreateProductRequest {
    private String name;
    private BigDecimal price;
    private int quantity;
    private String ProductCategory;
    @NotNull
    private MultipartFile image;
}
