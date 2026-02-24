package com.medikea.offers.service;

import com.medikea.offers.dto.OfferValidateRequest;
import com.medikea.offers.dto.OfferValidateResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OfferEngineService {

    public OfferValidateResponse preview(OfferValidateRequest req) {
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal newTotal = req.getOriginalAmount();

        return new OfferValidateResponse(false, "validation stub: no offers yet", discount, newTotal);
    }
    
}
