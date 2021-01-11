package com.a2.userservice.dto;

import java.math.BigDecimal;

public class DiscountDto {

    private BigDecimal discount;

    public BigDecimal getDiscount() {
        return discount;
    }

    public DiscountDto() {

    }

    public DiscountDto(BigDecimal discount) {
        this.discount = discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
