package com.nexus.nexus_api.controller;

import com.nexus.nexus_api.dto.StockEntryRequestDto;
import com.nexus.nexus_api.dto.StockItemRequestDto;
import com.nexus.nexus_api.dto.StockMovementResponseDto;
import com.nexus.nexus_api.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockMovementController {
    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping("/entry")
    public ResponseEntity<List<StockMovementResponseDto>> addStock(@RequestBody @Valid StockEntryRequestDto dto){
        List<StockMovementResponseDto> response = stockMovementService.save(dto);
        return ResponseEntity.status(201).body(response);
    }

}
