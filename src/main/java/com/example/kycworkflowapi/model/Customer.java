package com.example.kycworkflowapi.model;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String country;
    private String riskFlag;

    public Customer() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCountry() { return country; }
    public String getRiskFlag() { return riskFlag; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setCountry(String country) { this.country = country; }
    public void setRiskFlag(String riskFlag) { this.riskFlag = riskFlag; }
}
