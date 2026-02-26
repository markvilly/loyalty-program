package com.medikea.offers.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferClass offerClass;

    @Column(unique = true)
    private String code; // only for COUPON

    @Enumerated(EnumType.STRING)
    private OfferType offerType; // PERCENTAGE OR FIXED_AMOUNT

    @Column(nullable = false, name="discount_value", precision = 19, scale=2)
    private BigDecimal value;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(nullable = false)
    private boolean active = true;

    public Offer() {}

    public Long getId() {return id;}

    public OfferClass getOfferClass() {return offerClass;}

    public void setOfferClass(OfferClass offerClass) {this.offerClass = offerClass;}

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public OfferType getOfferType() {return offerType;}

    public void setOfferType(OfferType offerType) {this.offerType = offerType;}

    public BigDecimal getValue() {return value;}

    public void setValue(BigDecimal value) {this.value = value;}

    public LocalDateTime getStartDate() {return startDate;}

    public void setStartDate(LocalDateTime startDate) {this.startDate = startDate;}

    public LocalDateTime getEndDate() {return endDate;}

    public void setEndDate(LocalDateTime endDate) {this.endDate = endDate;}

    public boolean isActive() {return active;}

    public void setActive(boolean active) {this.active = active;


    }

}
