package com.nexus.nexus_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String productName;
    private String productBrand;
    private String productDescription;
    private BigDecimal productPrice;
    private int stockQuantity;
    private boolean productIsActive;
}
