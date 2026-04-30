package com.example.kycworkflowapi.service;

import com.example.kycworkflowapi.model.Customer;
import com.example.kycworkflowapi.model.KycResult;
import com.example.kycworkflowapi.repository.CustomerRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.List;

@Service
public class KycService {

    private static final Logger log = LoggerFactory.getLogger(KycService.class);

    private final CustomerRepository customerRepository;

    public KycService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        log.info("Creating customer: {}", customer.getName());
        return customerRepository.save(customer);
    }

    public List<Customer> createCustomers(List<Customer> customers) {
        log.info("Bulk creating {} customers", customers.size());
        return customerRepository.saveAll(customers);
    }

    public Optional<Customer> getCustomer(Long id) {
        log.info("Fetching customer with id {}", id);
        return customerRepository.findById(id);
    }

    public KycResult validateCustomer(Long customerId) {
        log.info("Validating customer ID {}", customerId);

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
