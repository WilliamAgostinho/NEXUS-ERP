package com.nexus.nexus_api.service;

import com.nexus.nexus_api.entity.Customer;
import com.nexus.nexus_api.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    public final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerByName(String name){
       return customerRepository.findBycustomerName(name)
               .orElseThrow(()  -> new RuntimeException("Any customer found with name: " + name));
    }

    public Customer findCustomerBycustomerDocument(String document) {
        return customerRepository.findBycustomerDocument(document)
                .orElseThrow(() -> new RuntimeException("Any customer found with document: " + document));
    }

    public Customer findByIdOrThrow (Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteByid(Long id) {
        customerRepository.deleteById(id);
    }
}
