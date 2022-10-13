package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dtos.requests.CustomerProfileUpdateRequest;
import africa.semicolon.lumexpress.data.dtos.requests.CustomerRegisterRequest;
import africa.semicolon.lumexpress.data.dtos.requests.EmailNotificationRequest;
import africa.semicolon.lumexpress.data.dtos.responses.CustomerRegisterResponse;
import africa.semicolon.lumexpress.data.models.Address;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import africa.semicolon.lumexpress.services.notifications.EmailNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ModelMapper model = new ModelMapper();

    private final CartService cartService;
    private final VerificationTokenService verificationTokenService;
    private final EmailNotificationService emailNotificationService;
    @Override
    public CustomerRegisterResponse register(CustomerRegisterRequest registerRequest) throws MessagingException {
        Customer customer = model.map(registerRequest, Customer.class);
        customer.setCart(new Cart());
        setCustomerAddress(registerRequest, customer);
        Customer savedCustomer = customerRepository.save(customer);
        var token = verificationTokenService.createToken(savedCustomer.getEmail());
        log.info("Customer to be saved in db::{}", savedCustomer);
        emailNotificationService.sendHtmlMail(buildEmailNotificationRequest(token, savedCustomer.getFirstName()+ " "+ savedCustomer.getLastName()));
        return customerRegisterResponseBuilder(savedCustomer);
    }

    private EmailNotificationRequest buildEmailNotificationRequest(VerificationToken verificationToken, String customerName) {
        log.info("token-->{}", verificationToken);
        var email = getEmailTemplate();
        String mail = null;
        if (email!=null){
            mail = String.format(email, customerName, "http://localhost:/8080/api/v1/customer/verify/" + verificationToken.getToken());
        }
        return EmailNotificationRequest.builder()
                .userEmail(verificationToken.getUserEmail())
                .mailContent(mail)
                .build();
    }

    private String getEmailTemplate(){
        try(BufferedReader bufferedReader = new BufferedReader( new FileReader("C:\\Users\\Amos Khaled\\IdeaProjects\\lum-express\\src\\main\\resources\\welcome.txt"))){
            return bufferedReader.lines().collect(Collectors.joining());
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void setCustomerAddress(CustomerRegisterRequest registerRequest, Customer customer) {
        var customerAddress = new Address();
        customerAddress.setCountry(registerRequest.getCountry());
        customer.getAddress().add(customerAddress);
    }

    private CustomerRegisterResponse customerRegisterResponseBuilder(Customer customer){
        return CustomerRegisterResponse.builder()
                .message("Success")
                .userId(customer.getId())
                .code(201)
                .build();
    }

    @Override
    public String updateProfile(CustomerProfileUpdateRequest updateRequest) {
        return null;
    }
}
