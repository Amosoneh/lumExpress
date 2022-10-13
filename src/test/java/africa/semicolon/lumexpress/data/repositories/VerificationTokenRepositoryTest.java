package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Address;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.exceptions.VerificationTokenException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class VerificationTokenRepositoryTest {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    private CustomerRepository customerRepository;
    private VerificationToken verificationToken;
    private Customer customer;
    @BeforeEach
    void setUp() {
//        Address address = new Address();
//        address.setCity("Lagos");
//        address.setCountry("Nigeria");
//        address.setStreet("312 habert road sabo");
//        customer.setFirstName("Amos");
//        customer.setLastName("Oneh");
//        customer.setEmail("amoskhaled@gmail.com");
//        customer.setCart(new Cart());
//       customer = customerRepository.save(customer);
//        customer.setAddress(address);
        verificationToken = new VerificationToken();
        verificationToken.setToken("12345");
        verificationToken.setUserEmail("amoskhaled@gmail.com");
        verificationTokenRepository.deleteAll();
    }

    @Test
    void findUserByEmailTest() {
        verificationTokenRepository.save(verificationToken);
        var foundToken = verificationTokenRepository.findByUserEmail("amoskhaled@gmail.com")
                .orElseThrow(()->new VerificationTokenException("Token not found"));
        log.info("foundToken -> {}",foundToken);
        assertThat(foundToken).isNotNull();
        assertThat(foundToken.getToken()).isEqualTo(verificationToken.getToken());
    }

    @Test
    void findByToken() {
        verificationTokenRepository.save(verificationToken);
        var token = verificationTokenRepository.findByToken(verificationToken.getToken())
                .orElseThrow(()-> new VerificationTokenException("Token not found"));
        assertThat(token).isNotNull();
        assertThat(token.getToken()).isEqualTo("12345");
    }
}