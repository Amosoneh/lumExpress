package africa.semicolon.lumexpress.data.repositories;

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
    private VerificationToken verificationToken;

    @BeforeEach
    void setUp() {
        verificationToken = new VerificationToken();
        verificationToken.setToken("12345");
        verificationToken.setUserEmail("test@email.com");
    }

    @Test
    void findByUserEmail() {
        verificationTokenRepository.save(verificationToken);
        var foundToken = verificationTokenRepository.findByUserEmail("test@email.com")
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