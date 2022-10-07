package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.requests.GetAllItemRequest;
import africa.semicolon.lumexpress.data.dtos.requests.UpdateProductRequest;
import africa.semicolon.lumexpress.data.dtos.responses.CreateProductResponse;
import africa.semicolon.lumexpress.data.dtos.responses.UpdateProductResponse;
import africa.semicolon.lumexpress.data.models.Product;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ProductService {
    CreateProductResponse createProduct (CreateProductRequest request) throws IOException;
    UpdateProductResponse updateProduct (Long productId, JsonPatch patch);
    Product getProductById(Long id);
    Page<Product> getAllProducts(GetAllItemRequest request);
}
