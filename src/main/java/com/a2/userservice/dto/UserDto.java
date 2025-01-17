package com.a2.userservice.dto;


import java.util.List;

public class UserDto {

    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String passportNo;
    private List<CardDto> cardDtos;

    public UserDto() {
    }

    public UserDto(Long userId, String email, String firstName, String lastName, String passportNo) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNo = passportNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public List<CardDto> getCardDtos() {
        return cardDtos;
    }

    public void setCardDtos(List<CardDto> cardDtos) {
        this.cardDtos = cardDtos;
    }
}
