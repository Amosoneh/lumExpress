package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.CreateProductRequest;
import africa.semicolon.lumexpress.data.dtos.requests.GetAllItemRequest;
import africa.semicolon.lumexpress.data.dtos.responses.CreateProductResponse;
import africa.semicolon.lumexpress.data.dtos.responses.UpdateProductResponse;
import africa.semicolon.lumexpress.data.models.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class productServiceImplTest {

    @Autowired
    private ProductService productService;
    CreateProductRequest request;
    CreateProductResponse response;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("C:\\Users\\Amos Khaled\\IdeaProjects\\lum-express\\src\\main\\resources\\static\\testProduct.jpg");
        MultipartFile file = new MockMultipartFile("peak", Files.readAllBytes(path));
        request = CreateProductRequest.builder()
                .ProductCategory("Fashion")
                .name("Amos")
                .image(file)
                .price(BigDecimal.valueOf(300.00))
                .build();
        response = productService.createProduct(request);
    }

    @Test
    void createProduct() throws IOException {
        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isGreaterThan(0L);
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getCode()).isEqualTo(201);

    }

    @Test
    void updateProduct() {
        ObjectMapper mapper = new ObjectMapper();
        UpdateProductResponse updateProductResponse = null;
        try {
            JsonNode value = mapper.readTree("\"eggs\"");
            JsonPatch patch = new JsonPatch(List.of(new ReplaceOperation(new JsonPointer("/name"), value)));
            updateProductResponse = productService.updateProduct(1L, patch);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertThat(updateProductResponse).isNotNull();
        assertThat(updateProductResponse.getStatusCode()).isEqualTo(200);
        assertThat(productService.getProductById(1L).getName()).isEqualTo("eggs");
    }

    @Test
    void getProductById() throws IOException {
        CreateProductResponse createProductResponse = productService.createProduct(request);
        Product foundProduct = productService.getProductById(createProductResponse.getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(createProductResponse.getProductId());
    }

    @Test
    void getAllProduct() throws IOException {
        productService.createProduct(request);
        var getItemRequest = buildGetItemsRequest();
        Page<Product> productPage = productService.getAllProducts(getItemRequest);
        assertThat(productPage).isNotNull();
        assertThat(productPage.getTotalElements()).isGreaterThan(0);
    }
    private GetAllItemRequest buildGetItemsRequest(){
        return GetAllItemRequest.builder()
                .numberOfItemsPerPage(5)
                .pageNumber(1)
                .build();
    }
}