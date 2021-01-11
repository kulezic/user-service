package com.a2.userservice.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class UserRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer minNumberOfMiles;
    private Integer maxNumberOfMiles;
    private Double discount;

    public UserRank() {
    }

    public UserRank(String name, Integer minNumberOfMiles, Integer maxNumberOfMiles, Double discount) {
        this.name = name;
        this.minNumberOfMiles = minNumberOfMiles;
        this.maxNumberOfMiles = maxNumberOfMiles;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinNumberOfMiles() {
        return minNumberOfMiles;
    }

    public void setMinNumberOfMiles(Integer minNumberOfMiles) {
        this.minNumberOfMiles = minNumberOfMiles;
    }

    public Integer getMaxNumberOfMiles() {
        return maxNumberOfMiles;
    }

    public void setMaxNumberOfMiles(Integer maxNumberOfMiles) {
        this.maxNumberOfMiles = maxNumberOfMiles;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
