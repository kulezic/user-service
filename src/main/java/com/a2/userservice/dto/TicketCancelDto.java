package com.a2.userservice.dto;

import java.math.BigDecimal;

public class TicketCancelDto {
    private Long userId;
    private Long miles;

    public TicketCancelDto(Long userId, Long miles) {
        this.userId = userId;
        this.miles = miles;
    }

    public TicketCancelDto(Long userId, BigDecimal miles) {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMiles() {
        return miles;
    }

    public void setMiles(Long miles) {
        this.miles = miles;
    }
}
