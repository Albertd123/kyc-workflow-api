package com.example.kycworkflowapi.repository;

import com.example.kycworkflowapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
