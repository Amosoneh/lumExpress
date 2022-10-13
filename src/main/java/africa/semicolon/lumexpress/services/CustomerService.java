package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.CustomerProfileUpdateRequest;
import africa.semicolon.lumexpress.data.dtos.requests.CustomerRegisterRequest;
import africa.semicolon.lumexpress.data.dtos.responses.CustomerRegisterResponse;

import javax.mail.MessagingException;

public interface CustomerService {
    CustomerRegisterResponse register(CustomerRegisterRequest registerRequest) throws MessagingException;
    String updateProfile(CustomerProfileUpdateRequest updateRequest);
}
