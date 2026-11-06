package com.nexus.nexus_api.controller;

import com.nexus.nexus_api.dto.SaleRequestDto;
import com.nexus.nexus_api.dto.SaleResponseDto;
import com.nexus.nexus_api.entity.Sale;
import com.nexus.nexus_api.entity.enums.SaleStatus;
import com.nexus.nexus_api.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<SaleResponseDto> getAllSale() {
       return saleService.findAll();
    }

    @GetMapping("{id}")
    public Sale getSaleById(@PathVariable Long id) {
       return saleService.findByIdOrThrow(id);
    }

    @GetMapping("/sale/status/{status}")
    public List<Sale> getSaleByStatus(@PathVariable String status) {
        return saleService.findSaleBystatus(SaleStatus.valueOf(status));
    }

    @PostMapping
    public ResponseEntity<SaleResponseDto> createSale(@RequestBody @Valid SaleRequestDto dto) {
        SaleResponseDto response = saleService.createSale(dto);
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<SaleResponseDto> completeSale(@PathVariable Long id) {
        SaleResponseDto response = saleService.completeSale(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<SaleResponseDto> cancelSale(@PathVariable Long id) {
        SaleResponseDto response = saleService.cancelSale(id);
        return ResponseEntity.ok(response);
    }
}
