package com.nexus.nexus_api.repository;

import com.nexus.nexus_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByproductName(String name);
    Optional<Product> findByproductBrand(String brand);
}
