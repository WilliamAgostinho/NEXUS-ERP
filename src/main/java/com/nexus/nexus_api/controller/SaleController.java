package com.nexus.nexus_api.controller;

import com.nexus.nexus_api.entity.Sale;
import com.nexus.nexus_api.entity.enums.SaleStatus;
import com.nexus.nexus_api.service.SaleService;
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
    public List<Sale> getAllSale() {
       return saleService.findAll();
    }

    @GetMapping("/sale/{id}")
    public Sale getSaleById(@PathVariable Long id) {
       return saleService.findByIdOrThrow(id);
    }

    @GetMapping("/sale/status/{status}")
    public List<Sale> getSaleByStatus(@PathVariable String status) {
        return saleService.findSaleBystatus(SaleStatus.valueOf(status));
    }

    @PostMapping("/sale")
    public Sale createSale(
            @RequestParam("customerId") Long customerId,
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("paymentMethod") String paymentMethod
    ) {
        return saleService.createSale(customerId, employeeId, paymentMethod);
    }

    @PatchMapping("/{id}/complete")
    public Sale completeSale(@PathVariable Long id) {
        return saleService.completeSale(id);
    }

    @PatchMapping("/{id}/cancel")
    public Sale cancelSale(@PathVariable Long id) {
        return saleService.cancelSale(id);
    }
}
