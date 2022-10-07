package africa.semicolon.lumexpress.data.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @Builder
public class GetAllItemRequest {
    private int numberOfItemsPerPage;
    private int pageNumber;
}
