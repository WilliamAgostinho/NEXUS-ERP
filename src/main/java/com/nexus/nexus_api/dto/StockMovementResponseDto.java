package com.nexus.nexus_api.dto;

import com.nexus.nexus_api.entity.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StockMovementResponseDto {
    private Long id;
    private Long productId;
    private Long employeeId;
    private MovementType movementType;
    private Integer quantity;
    private LocalDateTime movementDate;
}
