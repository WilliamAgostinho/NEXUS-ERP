package com.nexus.nexus_api.controller;

import com.nexus.nexus_api.dto.CustomerRequestDto;
import com.nexus.nexus_api.dto.CustomerResponseDto;
import com.nexus.nexus_api.entity.Customer;
import com.nexus.nexus_api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponseDto> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable Long id){
        CustomerResponseDto response = customerService.findCustomerById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CustomerResponseDto> findByName(@PathVariable String name){
        CustomerResponseDto response = customerService.findCustomerByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/document/{document}")
    public ResponseEntity<CustomerResponseDto> findByDocument(@PathVariable String document){
        CustomerResponseDto response = customerService.findBycustomerDocument(document);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody @Valid CustomerRequestDto dto) {
        CustomerResponseDto response = customerService.save(dto);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerRequestDto dto) {

        Customer existingCustomer = customerService.findByIdOrThrow(id);

        existingCustomer.setCustomerEmail(dto.getCustomerEmail());
        existingCustomer.setCustomerName(dto.getCustomerName());
        existingCustomer.setCustomerDocument(dto.getCustomerDocument());
        existingCustomer.setCustomerPhone(dto.getCustomerPhone());
        existingCustomer.setCustomerIsActive(dto.isCustomerIsActive());

        CustomerResponseDto response = customerService.save(existingCustomer);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteByid(id);
        return ResponseEntity.noContent().build();
    }

}
