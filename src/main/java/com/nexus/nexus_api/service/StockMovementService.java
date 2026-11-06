package com.nexus.nexus_api.service;

import com.nexus.nexus_api.dto.StockEntryRequestDto;
import com.nexus.nexus_api.dto.StockItemRequestDto;
import com.nexus.nexus_api.dto.StockMovementResponseDto;
import com.nexus.nexus_api.entity.Employee;
import com.nexus.nexus_api.entity.Product;
import com.nexus.nexus_api.entity.StockMovement;
import com.nexus.nexus_api.entity.enums.MovementType;
import com.nexus.nexus_api.exception.ResourceNotFoundException;
import com.nexus.nexus_api.repository.EmployeeRepository;
import com.nexus.nexus_api.repository.ProductRepository;
import com.nexus.nexus_api.repository.StockMovementRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockMovementService {
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;
    private final StockMovementRepository stockMovementRepository;

    public StockMovementService(ProductRepository productRepository, EmployeeRepository employeeRepository, StockMovementRepository stockMovementRepository) {
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    @Transactional
    public List<StockMovementResponseDto> save (@Valid @NonNull StockEntryRequestDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + dto.getEmployeeId()));

        List<StockMovement> movements = new ArrayList<>();

        for (StockItemRequestDto item : dto.getItems()){
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + item.getProductId()));


            product.increaseStock(item.getQuantity());

            StockMovement movement = new StockMovement();

            movement.setEmployee(employee);
            movement.setProduct(product);
            movement.setQuantity(item.getQuantity());
            movement.setMovementType(MovementType.IN);

            movements.add(movement);
        }

        List<StockMovement> savedMovement = stockMovementRepository.saveAll(movements);

        return savedMovement.stream()
                .map(this::toDto)
                .toList();
    }

    private @NonNull StockMovementResponseDto toDto (StockMovement stockMovement) {
        return new StockMovementResponseDto(
                stockMovement.getId(),
                stockMovement.getProduct().getId(),
                stockMovement.getEmployee().getId(),
                stockMovement.getMovementType(),
                stockMovement.getQuantity(),
                stockMovement.getMovementDate()
        );
    }
}
