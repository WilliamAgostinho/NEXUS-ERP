package com.nexus.nexus_api.repository;

import com.nexus.nexus_api.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
