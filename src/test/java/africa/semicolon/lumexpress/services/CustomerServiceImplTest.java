package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.CustomerRegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
//        customerService = new CustomerServiceImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register(){
        CustomerRegisterRequest request = CustomerRegisterRequest
                                        .builder()
                                        .email("amos@gmail.com")
                                        .password("amos")
                                        .country("Nigeria")
                                        .build();
        customerService.register(request);
        assertNotNull(customerService);
    }

    @Test
    void login(){

    }
    @Test
    void update(){

    }
}