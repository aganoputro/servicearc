package com.servicearc.customer.controller;

import com.servicearc.customer.dto.request.CustomerRegistrationRequestDto;
import com.servicearc.customer.dto.response.ResponseDto;
import com.servicearc.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<ResponseDto> registerCustomer(@RequestBody CustomerRegistrationRequestDto customerRegistrationRequest) {
        log.info("new Customer Registration {}", customerRegistrationRequest);
        ResponseDto responseDto = customerService.registerCustomer(customerRegistrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
