package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.LoginRequest;
import africa.semicolon.lumexpress.data.dtos.responses.LoginResponse;

public interface Userservice {
    LoginResponse login(LoginRequest loginRequest);
}
