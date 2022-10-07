package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.CustomerProfileUpdateRequest;
import africa.semicolon.lumexpress.data.dtos.requests.CustomerRegisterRequest;
import africa.semicolon.lumexpress.data.dtos.requests.LoginRequest;
import africa.semicolon.lumexpress.data.dtos.responses.CustomerRegisterResponse;
import africa.semicolon.lumexpress.data.dtos.responses.LoginResponse;

public interface CustomerService {
    CustomerRegisterResponse register(CustomerRegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    String completeProfile(CustomerProfileUpdateRequest updateRequest);
}
