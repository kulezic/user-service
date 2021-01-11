package com.a2.userservice.dto;

public class DiscountDto {

    private Double discount;

    public Double getDiscount() {
        return discount;
    }

    public DiscountDto() {

    }

    public DiscountDto(Double discount) {
        this.discount = discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
