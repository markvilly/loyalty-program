package com.medikea.offers.repo;

import com.medikea.offers.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface OfferRepo extends JpaRepository<Offer, Long> {
    Optional<Offer> findByCodeIgnoreCase(String code);
}
