package com.nexus.nexus_api.dto;

import com.nexus.nexus_api.entity.enums.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class SaleResponseDto {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long employeeId;
    private String employeeName;
    private String paymentMethod;
    private BigDecimal totalAmount;
    private SaleStatus status;
    private LocalDateTime saleDate;
    private List<SaleItemResponseDto> items;
}