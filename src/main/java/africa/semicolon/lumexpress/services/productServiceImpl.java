package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.requests.GetAllItemRequest;
import africa.semicolon.lumexpress.data.dtos.responses.CreateProductResponse;
import africa.semicolon.lumexpress.data.dtos.responses.UpdateProductResponse;
import africa.semicolon.lumexpress.data.models.Category;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.data.repositories.ProductRepository;
import africa.semicolon.lumexpress.exceptions.ProductNotFoundException;
import africa.semicolon.lumexpress.services.cloud.CloudService;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class productServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final CloudService cloudService;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) throws IOException {
        Product product = mapper.map(request, Product.class);
        product.getCategory().add(Category.valueOf(request.getProductCategory().toUpperCase()));
        var imageUrl = cloudService.upload(request.getImage().getBytes(), ObjectUtils.emptyMap());
        product.setImageUrl(imageUrl);
        Product savedProduct = productRepository.save(product);
        return buildCreateProductResponse(savedProduct);
    }
    private CreateProductResponse buildCreateProductResponse(Product product){
        return CreateProductResponse.builder()
                .productId(product.getId())
                .code(201)
                .message("Product created successfully")
                .build();
    }

    @Override
    public UpdateProductResponse updateProduct(Long productId, JsonPatch patch) {
        var foundProduct = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException(String.format("Product with id %d not found", productId)));
        Product updatedProduct = applyPatchToProduct(patch, foundProduct);
        var savedProduct = productRepository.save(updatedProduct);
        return buildUpdateResponse(savedProduct);
    }
    private Product applyPatchToProduct(JsonPatch patch, Product foundProduct){
        var productNode = objectMapper.convertValue(foundProduct, JsonNode.class);
        try {
            var patchedProductNode = patch.apply(productNode);
            var updatedProduct = objectMapper.readValue(objectMapper.writeValueAsBytes(patchedProductNode), Product.class);
            return updatedProduct;
        }catch (IOException | JsonPatchException e){
            e.printStackTrace();
            return null;
        }
    }
    private static UpdateProductResponse buildUpdateResponse(Product savedProduct){
        return UpdateProductResponse.builder()
                .productName(savedProduct.getName())
                .price(savedProduct.getPrice())
                .message("Update Successful")
                .statusCode(200)
                .build();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(
                        String.format("Product with id %d not found", id)
                )
        );
    }

    @Override
    public Page<Product> getAllProducts(GetAllItemRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNumber()-1, request.getNumberOfItemsPerPage());
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }
}
