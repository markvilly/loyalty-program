package com.medikea.offers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offers")
public class OffersHealthController {
     
    @GetMapping("/health")
    public String  health(){
        return "Offers module ok";
    }
}