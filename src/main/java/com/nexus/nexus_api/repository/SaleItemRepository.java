package com.nexus.nexus_api.repository;

import com.nexus.nexus_api.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
