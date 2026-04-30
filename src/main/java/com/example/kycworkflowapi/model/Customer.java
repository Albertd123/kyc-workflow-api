package com.example.kycworkflowapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String country;

    @NotBlank
    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "riskFlag must be LOW, MEDIUM, or HIGH")
    private String riskFlag;

    @Email
    private String email;

    @Pattern(regexp = "^[+0-9\\- ]+$", message = "phone must contain only digits, spaces, + or -")
    private String phone;

    public Customer() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCountry() { return country; }
    public String getRiskFlag() { return riskFlag; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setCountry(String country) { this.country = country; }
    public void setRiskFlag(String riskFlag) { this.riskFlag = riskFlag; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
}
