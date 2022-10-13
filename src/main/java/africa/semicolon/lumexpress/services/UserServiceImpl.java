package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.LoginRequest;
import africa.semicolon.lumexpress.data.dtos.responses.LoginResponse;
import africa.semicolon.lumexpress.data.models.Admin;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.models.LumAppUser;
import africa.semicolon.lumexpress.data.models.Vendor;
import africa.semicolon.lumexpress.data.repositories.AdminRepository;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import africa.semicolon.lumexpress.data.repositories.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @AllArgsConstructor
public class UserServiceImpl implements Userservice{
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Customer> customer = customerRepository.findByEmail(loginRequest.getEmail());
        if (customer.isPresent() && customer.get().getPassword().equals(loginRequest.getPassword())) return buildLogResponse(customer.get());
        Optional<Admin> admin = adminRepository.findByEmail(loginRequest.getEmail());
        if (admin.isPresent() && admin.get().getPassword().equals(loginRequest.getPassword())) return buildLogResponse(admin.get());
        Optional<Vendor> vendor = vendorRepository.findByEmail(loginRequest.getEmail());
        if (vendor.isPresent() && vendor.get().getPassword().equals(loginRequest.getPassword())) return buildLogResponse(vendor.get());
        return LoginResponse.builder()
                .code(400)
                .message("Login fail, bad credential")
                .build();
    }

    private LoginResponse buildLogResponse(LumAppUser customer) {
        return LoginResponse.builder()
                .message("User login successfully")
                .code(200)
                .build();
    }
}
