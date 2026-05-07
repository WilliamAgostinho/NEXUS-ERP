package com.nexus.nexus_api.service;

import com.nexus.nexus_api.entity.Product;
import com.nexus.nexus_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findByproductName(String productName) {
        return productRepository.findByproductName(productName)
            .orElseThrow(() -> new RuntimeException("Any product found with name: " + productName));
    }

    public Product findByproductBrand(String productBrand) {
        return productRepository.findByproductBrand(productBrand)
                .orElseThrow(() -> new RuntimeException("Any product found with brand: " + productBrand));
    }

    public Product findByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Any product found with id: " + id));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteByid(Long id) {
        productRepository.deleteById(id);
    }
}
