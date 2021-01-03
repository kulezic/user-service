package com.a2.userservice.dto;


public class CancelMilesDto {
    private Long userId;
    private Long miles;

    public CancelMilesDto(Long userId,
                          Long miles) {
        this.userId = userId;
        this.miles = miles;
    }

    public CancelMilesDto() {
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
