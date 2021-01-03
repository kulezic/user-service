package com.a2.userservice.mapper;

import com.a2.userservice.domain.Card;
import com.a2.userservice.domain.User;
import com.a2.userservice.dto.CardDto;
import com.a2.userservice.dto.CreateCardDto;
import com.a2.userservice.dto.UserCreateDto;
import com.a2.userservice.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDto cardToCardDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setCardHolderName(card.getCardHolderName());
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCif(card.getCif());
        return cardDto;
    }

    public Card cardCreateDtoToCard(CreateCardDto cardCreateDto) {
        Card card = new Card();
        card.setCif(cardCreateDto.getCif());
        card.setCardHolderName(cardCreateDto.getCardHolderName());
        card.setCardNumber(cardCreateDto.getCardNumber());
        return card;
    }

}
