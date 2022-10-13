package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.data.repositories.VerificationTokenRepository;
import africa.semicolon.lumexpress.exceptions.VerificationTokenException;
import africa.semicolon.lumexpress.utils.LumExpressUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService{
    private final VerificationTokenRepository verificationTokenRepository;
    @Override
    public VerificationToken createToken(String userEmail) {
        var token = VerificationToken.builder()
                .token(LumExpressUtils.generateToken())
                .userEmail(userEmail)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .build();
        return verificationTokenRepository.save(token);
    }

    @Override
    public boolean isValidVerificationToken(String token) {
        var foundToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(()-> new VerificationTokenException("Token not found"));
        if (isTokenNotExpired(foundToken)) return true;
        throw new VerificationTokenException("Token has expired");
    }

    private boolean isTokenNotExpired(VerificationToken verificationToken) {
        return LocalDateTime.now().isBefore(verificationToken.getExpiresAt());
    }
}
