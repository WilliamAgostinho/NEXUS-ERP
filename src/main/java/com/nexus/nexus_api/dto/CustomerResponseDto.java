package com.nexus.nexus_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomerResponseDto {
    private Long id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerDocument;
    private boolean customerIsActive;
    private LocalDateTime createdAt;
}
