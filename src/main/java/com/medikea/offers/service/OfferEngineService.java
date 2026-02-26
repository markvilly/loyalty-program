package com.medikea.offers.service;

import com.medikea.offers.domain.Offer;
import com.medikea.offers.domain.OfferClass;
import com.medikea.offers.domain.OfferType;
import com.medikea.offers.domain.OfferUsage;
import com.medikea.offers.dto.OfferValidateRequest;
import com.medikea.offers.dto.OfferValidateResponse;
import com.medikea.offers.repo.OfferRepo;
import com.medikea.offers.repo.OfferUsageRepo;

import java.util.Optional;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OfferEngineService {

    private final OfferRepo offerRepo;
    private final OfferUsageRepo offerUsageRepo;

    public OfferEngineService(OfferRepo offerRepo, OfferUsageRepo offerUsageRepo){
        this.offerRepo = offerRepo;
        this.offerUsageRepo = offerUsageRepo;
    }

    public OfferValidateResponse preview(OfferValidateRequest req) {
        
        if(req.getCouponCode() == null || req.getCouponCode().isBlank()){
            return new OfferValidateResponse(
                false,
                "No coupon code provided",
                BigDecimal.ZERO,
                req.getOriginalAmount()
            );
        }

        Optional<Offer> optionalOffer = offerRepo.findByCodeIgnoreCaseAndActiveTrue(req.getCouponCode());

        if(optionalOffer.isEmpty()){
            return new OfferValidateResponse(
                false,
                "Invalid coupon code",
                BigDecimal.ZERO,
                req.getOriginalAmount()
            );
        }

        Offer offer = optionalOffer.get();

        // Enforce Date validation
        if(!isWithinActiveWindow(offer)){
            return new OfferValidateResponse(
                false,
                "Coupon is not active (outside valid date range)",
                BigDecimal.ZERO,
                req.getOriginalAmount()
            );
        }

        //Enforce Limit
       if(offer.getLimit() != null){
        long usageCount = offerUsageRepo.countByOfferIdAndUserId(offer.getId(), req.getUserId());

        if(usageCount >= offer.getLimit()){
            return new OfferValidateResponse(
                false,
                "Coupon limit reached",
                BigDecimal.ZERO,
                req.getOriginalAmount()
            );
        }
       }

        // Make sure it's a COUPON type.
        if(offer.getOfferClass() != OfferClass.COUPON){
            return new OfferValidateResponse(
                false,
                "This offer is not a coupon",
                BigDecimal.ZERO,
                req.getOriginalAmount()
            );
        }
        

        BigDecimal discount = calculateDiscount(offer, req.getOriginalAmount());
        BigDecimal newTotal = req.getOriginalAmount().subtract(discount).max(BigDecimal.ZERO);

        offerUsageRepo.save(OfferUsage.builder()
        .offer(offer)
        .userId(req.getUserId())
        .discountApplied(discount)
        .serviceType(req.getServiceType())
        .usedAt(java.time.LocalDateTime.now())
        .build()
    
    );


        return new OfferValidateResponse(true, "Coupon applied successfully", discount, newTotal);
    }

    private boolean isWithinActiveWindow(Offer offer) {
        var now = java.time.LocalDateTime.now();

        var start = offer.getStartDate();
        if(start != null && now.isBefore(start)) return false;
        
        var end = offer.getEndDate();
        if(end != null && now.isAfter(end)) return false;
        
        return true;
    }

    public BigDecimal calculateDiscount(Offer offer, BigDecimal amount) {

        if(offer.getOfferType() == OfferType.PERCENTAGE){
            return amount.multiply(offer.getValue())
                        .divide(new BigDecimal("100"), 2 , java.math.RoundingMode.HALF_UP);
        }

        if(offer.getOfferType() == OfferType.FIXED_AMOUNT){
            return offer.getValue();
        }

        return BigDecimal.ZERO;
    }
}
