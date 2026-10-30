package com.nexus.nexus_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class SaleRequestDto {

    @NotNull(message = "Customer id is required")
    private Long customerId;

    @NotNull(message = "Employee id is required")
    private Long employeeId;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @NotNull
    private List<SaleItemRequestDto> items;
}