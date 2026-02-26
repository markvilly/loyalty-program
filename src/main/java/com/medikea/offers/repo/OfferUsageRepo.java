package com.medikea.offers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medikea.offers.domain.OfferUsage;

public interface OfferUsageRepo extends JpaRepository<OfferUsage, Long> {
    long countByOfferIdAndUserId(Long offerId, Long userId);
    long countByOfferId(Long offerid);
}
