package com.nexus.nexus_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmployeeResponseDto {
    private Long id;
    private String employeeName;
    private String employeeRole;
    private Boolean employeeIsActive;
    private BigDecimal employeeSalary;
    private LocalDateTime createdAt;
}
