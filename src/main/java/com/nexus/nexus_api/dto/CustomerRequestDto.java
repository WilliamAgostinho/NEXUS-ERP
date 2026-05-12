package com.nexus.nexus_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CustomerRequestDto {

    @NotEmpty(message = "Name is mandatory")
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private boolean customerIsActive;

    @NotEmpty(message = "Document is mandatory")
    private String customerDocument;

}
