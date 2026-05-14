package com.nexus.nexus_api.service;

import com.nexus.nexus_api.dto.ProductRequestDto;
import com.nexus.nexus_api.dto.ProductResponseDto;
import com.nexus.nexus_api.entity.Product;
import com.nexus.nexus_api.exception.ResourceNotFoundException;
import com.nexus.nexus_api.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto findByproductName(String productName) {
        Product product = productRepository.findByproductName(productName)
            .orElseThrow(() -> new ResourceNotFoundException("Any product found with name: " + productName));
        return toDto(product);
    }

    public ProductResponseDto findByproductBrand(String productBrand) {
        Product product = productRepository.findByproductBrand(productBrand)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with brand: " + productBrand));
        return toDto(product);
    }

    public Product findByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public ProductResponseDto findById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return toDto(product);
    }

    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::toDto)
                .toList();
    }

    public ProductResponseDto save(@Valid ProductRequestDto dto) {
        Product product = toEntity(dto);
        Product savedProduct = productRepository.save(product);
        return toDto(savedProduct);
    }

    public ProductResponseDto save(Product product) {
        Product savedProduct = productRepository.save(product);
        return toDto(savedProduct);
    }

    public void deleteByid(Long id) {
        productRepository.deleteById(id);
    }

    private Product toEntity (ProductRequestDto dto){
        Product product = new Product();
        product.setProductBrand(dto.getProductBrand());
        product.setProductName(dto.getProductName());
        product.setProductPrice(dto.getProductPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setProductDescription(dto.getProdctDescription());
        product.setProductIsActive(dto.isProdcutAtive());
        return product;
    }

    private ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getProductName(),
                product.getProductBrand(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getStockQuantity(),
                product.isProductIsActive()
        );
    }
}
