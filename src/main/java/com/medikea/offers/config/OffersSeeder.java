package com.medikea.offers.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.medikea.offers.domain.Offer;
import com.medikea.offers.domain.OfferClass;
import com.medikea.offers.domain.OfferType;
import com.medikea.offers.repo.OfferRepo;

import java.math.BigDecimal;


@Component
public class OffersSeeder implements CommandLineRunner {

    private final OfferRepo offerRepo;

    public OffersSeeder(OfferRepo offerRepo){
        this.offerRepo = offerRepo;
    }

    @Override
    public void run(String... args){

        try{
            offerRepo.findAll();
        } catch (Exception e){
            System.out.println("DB not ready / Entities not scanned: " + e.getMessage());
            return;
        }
        
        if (offerRepo.count() > 0) return;
        
        try{
        Offer coupon = new Offer();
        coupon.setOfferClass(OfferClass.COUPON);
        coupon.setCode("WELCOME20");
        coupon.setOfferType(OfferType.PERCENTAGE);
        coupon.setValue(new BigDecimal("20.00"));
        coupon.setActive(true);
        coupon.setEndDate(java.time.LocalDateTime.now().plusDays(30));
        coupon.setLimit(1);

        offerRepo.save(coupon);

        System.out.println("Seeded offer: WELCOME20");
        } catch (Exception e){
            System.out.println("Seeder skipped (DB not ready yet): " + e.getMessage());
        }


    }
    
}