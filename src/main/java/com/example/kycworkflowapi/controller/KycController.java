package com.example.kycworkflowapi.controller;

import com.example.kycworkflowapi.model.Customer;
import com.example.kycworkflowapi.model.KycResult;
import com.example.kycworkflowapi.service.KycService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/kyc")
public class KycController {

    private final KycService kycService;

    public KycController(KycService kycService) {
        this.kycService = kycService;
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(kycService.createCustomer(customer));
    }

    @PostMapping("/customers/bulk")
    public ResponseEntity<List<Customer>> createCustomers(
            @Valid @RequestBody List<Customer> customers) {
        return ResponseEntity.ok(kycService.createCustomers(customers));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = kycService.getCustomer(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/validate/{customerId}")
    public ResponseEntity<KycResult> validate(@PathVariable Long customerId) {
        return ResponseEntity.ok(kycService.validateCustomer(customerId));
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
