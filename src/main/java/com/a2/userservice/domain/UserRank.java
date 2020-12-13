package com.a2.userservice.domain;

import javax.persistence.*;
import java.math.BigDecimal;


public class UserRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer minNumberOfMiles;
    private Integer maxNumberOfMiles;
    private Integer discount;

    public UserRank() {
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
