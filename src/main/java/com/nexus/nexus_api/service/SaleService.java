package com.nexus.nexus_api.service;

import com.nexus.nexus_api.entity.Customer;
import com.nexus.nexus_api.entity.Employee;
import com.nexus.nexus_api.entity.Sale;
import com.nexus.nexus_api.entity.enums.SaleStatus;
import com.nexus.nexus_api.repository.CustomerRepository;
import com.nexus.nexus_api.repository.EmployeeRepository;
import com.nexus.nexus_api.repository.SaleRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Sale createSale(@NonNull Long customerId,@NonNull Long employeeId,@NonNull String paymentMethod){
        Sale sale = new Sale();

        Customer customer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new RuntimeException("No customer found with id: " + customerId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("No employee found with id: " + employeeId));

        sale.setCustomer(customer);
        sale.setEmployee(employee);
        sale.setPaymentMethod(paymentMethod);
        sale.setTotalAmount(BigDecimal.ZERO);
        sale.setStatus(SaleStatus.OPEN);
        saleRepository.save(sale);
        return sale;
    }

    public Sale completeSale(@NonNull Long saleId){
        Sale sale = new Sale();
        saleRepository.findById(saleId)
                    .orElseThrow(() -> new RuntimeException("No sale found with id: " + saleId));
        sale.setStatus(SaleStatus.COMPLETED);
        saleRepository.save(sale);
        sale.complete();
        return sale;
    }

    public Sale cancelSale(@NonNull Long saleId){
        Sale sale = new Sale();
        saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("No sale found with id: " + saleId));
        sale.setStatus(SaleStatus.CANCELLED);
        saleRepository.save(sale);
        sale.complete();
        return sale;
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findByIdOrThrow(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("No sale found with id: " + saleId));
    }

    public List<Sale> findSaleBystatus(SaleStatus saleStatus) {
        return saleRepository.findSalesByStatus(saleStatus);
    }
}
