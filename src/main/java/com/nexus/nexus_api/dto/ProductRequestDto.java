package com.nexus.nexus_api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    private int id;
    private String productName;
    private String productBrand;
    private String prodctDescription;
    private BigDecimal productPrice;
    private boolean isProdcutAtive;
    private int stockQuantity;
}
