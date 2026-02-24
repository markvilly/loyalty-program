package com.medikea.offers.dto;

import java.math.BigDecimal;

public class OfferValidateRequest {
    private Long userId;
    private String serviceType;      // keep String for now, we’ll upgrade to enum later
    private BigDecimal originalAmount;
    private String couponCode;       // optional

    public OfferValidateRequest() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public BigDecimal getOriginalAmount() { return originalAmount; }
    public void setOriginalAmount(BigDecimal originalAmount) { this.originalAmount = originalAmount; }

    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }
}