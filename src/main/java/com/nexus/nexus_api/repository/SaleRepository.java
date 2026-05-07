package com.nexus.nexus_api.repository;

import com.nexus.nexus_api.entity.Sale;
import com.nexus.nexus_api.entity.enums.SaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
        List<Sale> findSalesByStatus(SaleStatus status);
}
