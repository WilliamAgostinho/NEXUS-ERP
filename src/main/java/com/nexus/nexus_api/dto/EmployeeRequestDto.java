package com.nexus.nexus_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeRequestDto {
    @NotEmpty(message = "Name is mandatory")
    private String employeeName;
    public String employeeRole;
    public BigDecimal employeeSalary;
    public String employeePassword;
    public String employeeUserName;
    public Boolean employeeIsActive;
}
