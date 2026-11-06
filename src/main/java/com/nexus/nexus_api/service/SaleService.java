package com.nexus.nexus_api.service;

import com.nexus.nexus_api.dto.SaleItemRequestDto;
import com.nexus.nexus_api.dto.SaleItemResponseDto;
import com.nexus.nexus_api.dto.SaleRequestDto;
import com.nexus.nexus_api.dto.SaleResponseDto;
import com.nexus.nexus_api.entity.*;
import com.nexus.nexus_api.entity.enums.MovementType;
import com.nexus.nexus_api.entity.enums.SaleStatus;
import com.nexus.nexus_api.exception.ResourceNotFoundException;
import com.nexus.nexus_api.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    private final CustomerRepository customerRepository;

    private final EmployeeRepository employeeRepository;

    private final ProductRepository productRepository;

    private final StockMovementRepository stockMovementRepository;

    public SaleService(SaleRepository saleRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, ProductRepository productRepository, StockMovementRepository stockMovementRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    @Transactional
    public SaleResponseDto createSale(SaleRequestDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + dto.getCustomerId()));

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + dto.getEmployeeId()));

        Sale sale = new Sale();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (SaleItemRequestDto itemDto : dto.getItems()){

            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemDto.getProductId()));

            product.decreaseStock(itemDto.getQuantity());

            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(product);
            saleItem.setSaleItemQuantity(itemDto.getQuantity());
            saleItem.setSaleItemUnitPrice(product.getProductPrice());

            StockMovement stockMovement = new StockMovement();
            stockMovement.setEmployee(employee);
            stockMovement.setProduct(product);
            stockMovement.setQuantity(itemDto.getQuantity());
            stockMovement.setMovementType(MovementType.OUT);

            stockMovementRepository.save(stockMovement);

            sale.getItems().add(saleItem);

            BigDecimal subTotal = product.getProductPrice()
                    .multiply(BigDecimal.valueOf((itemDto.getQuantity())));

            totalAmount = totalAmount.add(subTotal);
        }

        sale.setCustomer(customer);
        sale.setEmployee(employee);
        sale.setPaymentMethod(dto.getPaymentMethod());
        sale.setStatus(SaleStatus.OPEN);
        sale.setTotalAmount(totalAmount);

        return toDto(saleRepository.save(sale));
    }

    public Sale completeSale(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("No sale found with id: " + saleId));
        sale.complete();
        return saleRepository.save(sale);
    }

    public Sale cancelSale(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("No sale found with id: " + saleId));
        sale.cancel();
        return saleRepository.save(sale);
    }

    public List<SaleResponseDto> findAll() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .map(this::toDto)
                .toList();
    }

    public Sale findByIdOrThrow(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("No sale found with id: " + saleId));
    }

    public List<Sale> findSaleBystatus(SaleStatus saleStatus) {
        return saleRepository.findSalesByStatus(saleStatus);
    }

    private SaleItemResponseDto toItemDto(SaleItem Item) {
        BigDecimal subTotal = Item.getSaleItemUnitPrice()
                .multiply(BigDecimal.valueOf((Item.getSaleItemQuantity())));

        return new SaleItemResponseDto(
            Item.getProduct().getId(),
            Item.getProduct().getProductName(),
            Item.getSaleItemQuantity(),
            Item.getSaleItemUnitPrice(),
            subTotal
        );
    }

    private SaleResponseDto toDto(Sale sale) {
        List<SaleItemResponseDto> itemDtos = sale.getItems().stream()
                .map(this::toItemDto)
                .toList();

        return new SaleResponseDto(
                sale.getId(),
                sale.getCustomer().getId(),
                sale.getCustomer().getCustomerName(),
                sale.getEmployee().getId(),
                sale.getEmployee().getEmployeeName(),
                sale.getPaymentMethod(),
                sale.getTotalAmount(),
                sale.getStatus(),
                sale.getSaleDate(),
                itemDtos
        );
    }
}
