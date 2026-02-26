package com.medikea.offers.service;

import com.medikea.offers.domain.Offer;
import com.medikea.offers.domain.OfferClass;
import com.medikea.offers.domain.OfferType;
import com.medikea.offers.dto.OfferValidateRequest;
import com.medikea.offers.dto.OfferValidateResponse;
import com.medikea.offers.repo.OfferRepo;
import java.util.Optional;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OfferEngineService {

    private final OfferRepo offerRepo;

    public OfferEngineService(OfferRepo offerRepo){
        this.offerRepo = offerRepo;
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
        BigDecimal newTotal = req.getOriginalAmount();

        System.out.println("Offer value: " + offer.getValue());
System.out.println("Offer type: " + offer.getOfferType());

        return new OfferValidateResponse(true, "Coupon applied successfully", discount, newTotal);
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
