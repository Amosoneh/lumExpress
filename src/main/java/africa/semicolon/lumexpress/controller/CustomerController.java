package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dtos.requests.CustomerRegisterRequest;
import africa.semicolon.lumexpress.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody CustomerRegisterRequest customerRegisterRequest) throws MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.register(customerRegisterRequest));
    }
}
