package com.a2.userservice.dto;

import java.math.BigDecimal;

public class UserRankDto {
    private String rankName;
    private Long miles;

    public UserRankDto() {
    }

    public UserRankDto(String rankName, Long miles) {
        this.rankName = rankName;
        this.miles = miles;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Long getMiles() {
        return miles;
    }

    public void setMiles(Long miles) {
        this.miles = miles;
    }
}
