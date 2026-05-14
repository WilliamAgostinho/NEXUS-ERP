package com.nexus.nexus_api.service;

import com.nexus.nexus_api.dto.CustomerRequestDto;
import com.nexus.nexus_api.dto.CustomerResponseDto;
import com.nexus.nexus_api.entity.Customer;
import com.nexus.nexus_api.exception.ResourceNotFoundException;
import com.nexus.nexus_api.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    public final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDto findBycustomerDocument(String document) {
        Customer customer = customerRepository.findBycustomerDocument(document)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with document: " + document));
        return toDto(customer);
    }

    public Customer findByIdOrThrow (Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    public CustomerResponseDto findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        return toDto(customer);
    }

    public List<CustomerResponseDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::toDto)
                .toList();
    }

    public CustomerResponseDto findCustomerByName(String name){

        Customer customer = customerRepository
                .findBycustomerName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with name " + name));

        return toDto(customer);
    }

    public CustomerResponseDto save(@Valid CustomerRequestDto dto) {
        Customer customer = toEntity(dto);
        Customer saved = customerRepository.save(customer);
        return toDto(saved);
    }

    public CustomerResponseDto save(Customer customer) {
        Customer saved = customerRepository.save(customer);
        return toDto(saved);
    }

    public void deleteByid(Long id) {
        customerRepository.deleteById(id);
    }

    private Customer toEntity (CustomerRequestDto dto){
        Customer customer = new Customer();
        customer.setCustomerName(dto.getCustomerName());
        customer.setCustomerEmail(dto.getCustomerEmail());
        customer.setCustomerPhone(dto.getCustomerPhone());
        customer.setCustomerDocument(dto.getCustomerDocument());
        return customer;
    }

    private CustomerResponseDto toDto (Customer customer){
        return new CustomerResponseDto(
                customer.getId(),
                customer.getCustomerName(),
                customer.getCustomerEmail(),
                customer.getCustomerDocument(),
                customer.getCustomerPhone(),
                customer.isCustomerIsActive()
        );
    }
}
