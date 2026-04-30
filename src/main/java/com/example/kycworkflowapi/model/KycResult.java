package com.example.kycworkflowapi.model;

public class KycResult {

    private Long customerId;
    private String status;
    private String reason;

    public KycResult() {}

    public KycResult(Long customerId, String status, String reason) {
        this.customerId = customerId;
        this.status = status;
        this.reason = reason;
    }

    public Long getCustomerId() { return customerId; }
    public String getStatus() { return status; }
    public String getReason() { return reason; }

    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setStatus(String status) { this.status = status; }
    public void setReason(String reason) { this.reason = reason; }
}
