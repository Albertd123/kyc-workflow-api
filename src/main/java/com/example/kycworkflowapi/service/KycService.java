package com.example.kycworkflowapi.service;

import com.example.kycworkflowapi.model.Customer;
import com.example.kycworkflowapi.model.KycResult;
import com.example.kycworkflowapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KycService {

    private final CustomerRepository customerRepository;

    public KycService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public KycResult validateCustomer(Long customerId) {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isEmpty()) {
            return new KycResult(customerId, "REJECTED", "Customer not found");
        }

        Customer c = opt.get();

        if (c.getName() == null || c.getName().isBlank()
                || c.getAddress() == null || c.getAddress().isBlank()) {
            return new KycResult(customerId, "REVIEW", "Missing required fields");
        }

        if ("HIGH".equalsIgnoreCase(c.getRiskFlag())) {
            return new KycResult(customerId, "REVIEW", "High risk flag");
        }

        return new KycResult(customerId, "APPROVED", "All checks passed");
    }
}
