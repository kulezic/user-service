package com.a2.userservice.dto;

import org.hibernate.validator.constraints.Length;

public class CreateCardDto {

    private String cardHolderName;
    @Length(min = 20, max = 20)
    private Long cardNumber;
    @Length(min = 3, max = 3)
    private Integer cif;

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCif() {
        return cif;
    }

    public void setCif(Integer cif) {
        this.cif = cif;
    }

}
