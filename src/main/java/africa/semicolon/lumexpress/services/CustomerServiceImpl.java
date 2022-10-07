package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.CustomerProfileUpdateRequest;
import africa.semicolon.lumexpress.data.dtos.requests.CustomerRegisterRequest;
import africa.semicolon.lumexpress.data.dtos.requests.LoginRequest;
import africa.semicolon.lumexpress.data.dtos.responses.CustomerRegisterResponse;
import africa.semicolon.lumexpress.data.dtos.responses.LoginResponse;
import africa.semicolon.lumexpress.data.models.Address;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ModelMapper model = new ModelMapper();

    private final CartService cartService;
    @Override
    public CustomerRegisterResponse register(CustomerRegisterRequest registerRequest) {
        Customer customer = model.map(registerRequest, Customer.class);
        customer.setCart(new Cart());
        var customerAddress = new Address();
        customerAddress.setCountry(registerRequest.getCountry());
        customer.getAddress().add(customerAddress);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer to be saved in db::{}", savedCustomer);
        return customerRegisterResponseBuilder(savedCustomer);
    }

    private CustomerRegisterResponse customerRegisterResponseBuilder(Customer customer){
        return CustomerRegisterResponse.builder()
                .message("Success")
                .userId(customer.getId())
                .code(201)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public String completeProfile(CustomerProfileUpdateRequest updateRequest) {
        return null;
    }
}
