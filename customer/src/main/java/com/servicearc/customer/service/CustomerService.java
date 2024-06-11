package com.servicearc.customer.service;

import com.servicearc.customer.dto.response.CustomerRegistrationResponseDto;
import com.servicearc.customer.dto.response.FraudCheckResponse;
import com.servicearc.customer.dto.response.ResponseDto;
import com.servicearc.customer.repository.CustomerRepository;
import com.servicearc.customer.dto.request.CustomerRegistrationRequestDto;
import com.servicearc.customer.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    public ResponseDto registerCustomer(CustomerRegistrationRequestDto request) {
        String encodedPassword = Base64.getEncoder().encodeToString(request.password().getBytes());
        Customer customer = Customer.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .phone(request.phone())
                .build();
        //todo: check email validity
        //todo: check is fraudster
        CustomerRegistrationResponseDto result = new CustomerRegistrationResponseDto(customer.getName(), customer.getEmail(), customer.getUniqueKey());
        if (customerRepository.findByEmail(request.email()).isPresent()){
            return new ResponseDto(false, 409, "Duplicate Record Found", result);
        } else {
            customerRepository.saveAndFlush(customer);
//            FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getUniqueKey());
//
//            HttpHeaders httpHeaders = new HttpHeaders();
//            Map<String, List<Integer>> requestBody = new HashMap<>();
//            HttpEntity<Map<String, List<Integer>>> requestEntity = new HttpEntity<>(requestBody,httpHeaders);
//            restTemplate.postForObject("http://NOTIFICATION/api/v1/notification", requestEntity, Object.class);
//            if (fraudCheckResponse.isFraudster()){
//                return new ResponseDto(true, 409, "Fraudster", result);
//            } else {
                return new ResponseDto(true, 200, "Success Register new Customer", result);
//            }
        }
    }
}
