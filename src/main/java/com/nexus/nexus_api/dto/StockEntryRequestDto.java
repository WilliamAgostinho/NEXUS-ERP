package com.nexus.nexus_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StockEntryRequestDto {
    @NotNull(message = "Emplyee Id is required")
    private Long employeeId;

    @NotNull
    @Valid
    private List<StockItemRequestDto> items;
}
