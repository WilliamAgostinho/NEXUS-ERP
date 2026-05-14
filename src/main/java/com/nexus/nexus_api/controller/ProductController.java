package com.nexus.nexus_api.controller;

import com.nexus.nexus_api.dto.ProductRequestDto;
import com.nexus.nexus_api.dto.ProductResponseDto;
import com.nexus.nexus_api.entity.Product;
import com.nexus.nexus_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
        ProductResponseDto response = productService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponseDto> finProductByName(@PathVariable String name){
        ProductResponseDto response = productService.findByproductName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<ProductResponseDto> findProductByBrand(@PathVariable String brand){
        ProductResponseDto response = productService.findByproductBrand(brand);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto dto) {
        ProductResponseDto response = productService.save(dto);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequestDto dto) {

        Product existingProduct = productService.findByIdOrThrow(id);

        existingProduct.setProductBrand(dto.getProductBrand());
        existingProduct.setProductName(dto.getProductName());
        existingProduct.setProductDescription(dto.getProdctDescription());
        existingProduct.setProductPrice(dto.getProductPrice());
        existingProduct.setStockQuantity(dto.getStockQuantity());

        return ResponseEntity.ok(productService.save(existingProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteByid(id);
        return ResponseEntity.noContent().build();
    }
}
