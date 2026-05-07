package com.nexus.nexus_api.controller;

import com.nexus.nexus_api.entity.Customer;
import com.nexus.nexus_api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/name/{name}")
    public Customer findByName(@PathVariable String name) {
        return customerService.findCustomerByName(name);
    }

    @GetMapping("/document/{document}")
    public Customer findByDocument(@PathVariable String document) {
        return customerService.findCustomerBycustomerDocument(document);
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer existingCustomer = customerService.findByIdOrThrow(id);

        existingCustomer.setCustomerEmail(customer.getCustomerEmail());
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setCustomerDocument(customer.getCustomerDocument());
        existingCustomer.setCustomerPhone(customer.getCustomerPhone());
        existingCustomer.setCustomerIsActive(customer.isCustomerIsActive());

        return customerService.save(existingCustomer);
    }

    @DeleteMapping("/deletecustomer/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteByid(id);
    }

}
