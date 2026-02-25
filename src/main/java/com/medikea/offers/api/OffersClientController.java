package com.medikea.offers.api;

import com.medikea.offers.dto.*;
import com.medikea.offers.service.OfferEngineService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offers")
public class OffersClientController {

    private final OfferEngineService offerEngineService;
    
    public OffersClientController(OfferEngineService offerEngineService){
        this.offerEngineService = offerEngineService;
    }

    @PostMapping("/validate")
    public OfferValidateResponse validateOffer(@RequestBody OfferValidateRequest req){
        return offerEngineService.preview(req);
    }
}
