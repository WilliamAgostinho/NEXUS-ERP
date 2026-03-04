package com.nexus.nexus_api.repository;

import com.nexus.nexus_api.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}
