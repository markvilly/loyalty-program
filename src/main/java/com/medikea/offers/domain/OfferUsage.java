package com.medikea.offers.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Offer offer;

    private Long userId;
    private BigDecimal discountApplied;
    private String serviceType;
    private LocalDateTime usedAt;
}
