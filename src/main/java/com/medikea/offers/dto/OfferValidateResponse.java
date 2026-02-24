package com.medikea.offers.dto;

import java.math.BigDecimal;


public class OfferValidateResponse {
    private boolean applied;
    private String message;
    private BigDecimal discountAmount;
    private BigDecimal newTotal;

    public OfferValidateResponse() {}

    public OfferValidateResponse(boolean applied, String message, BigDecimal discountAmount, BigDecimal newTotal){
        this.applied = applied;
        this.message = message;
        this.discountAmount = discountAmount;
        this.newTotal = newTotal;
    }

    public boolean isApplied() {
        return applied;

    }
    public void setApplied(boolean applied) { this.applied = applied; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }

    public BigDecimal getNewTotal() { return newTotal; }
    public void setNewTotal(BigDecimal newTotal) { this.newTotal = newTotal; }
    
}
