package repository;

import entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
