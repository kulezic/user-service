package com.a2.userservice.dto;

import org.hibernate.validator.constraints.Length;

public class CreateCardDto {

    private Long userId;
    private String cardHolderName;
    @Length(min = 10, max = 10)
    private Long cardNumber;
    @Length(min = 3, max = 3)
    private Integer cif;


    public CreateCardDto() {
    }

    public CreateCardDto(Long userId,
                         String cardHolderName,
                         @Length(min = 20, max = 20) Long cardNumber,
                         @Length(min = 3, max = 3) Integer cif) {
        this.userId = userId;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cif = cif;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
