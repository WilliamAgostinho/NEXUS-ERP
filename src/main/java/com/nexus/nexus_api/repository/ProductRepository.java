package com.nexus.nexus_api.repository;

import com.nexus.nexus_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
