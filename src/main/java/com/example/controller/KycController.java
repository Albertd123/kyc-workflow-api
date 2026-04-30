package com.example.controller;

import com.example.kycworkflowapi.model.Customer;
import com.example.kycworkflowapi.model.KycResult;
import com.example.kycworkflowapi.service.KycService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/kyc")
public class KycController {

    private final KycService kycService;

    public KycController(KycService kycService) {
        this.kycService = kycService;
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(kycService.createCustomer(customer));
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
}
