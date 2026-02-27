package com.medikea.offers.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "offer_usage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Offer offer;

    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal discountApplied;
    
    @Column(nullable = false)
    private String serviceType;
    
    @Column(nullable = false)
    private LocalDateTime usedAt;
}
