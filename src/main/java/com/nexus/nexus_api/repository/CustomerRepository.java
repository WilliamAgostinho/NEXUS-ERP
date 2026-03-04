package com.nexus.nexus_api.repository;

import com.nexus.nexus_api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
