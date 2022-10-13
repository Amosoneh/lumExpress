package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.CustomerRegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
//        customerService = new CustomerServiceImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() throws MessagingException {
        CustomerRegisterRequest request = CustomerRegisterRequest
                .builder()
                .email("amoskhaled@gmail.com")
                .firstName("Amos")
                .lastName("Oneh")
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