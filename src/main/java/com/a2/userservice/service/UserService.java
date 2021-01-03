package com.a2.userservice.service;

import com.a2.userservice.dto.*;

public interface UserService {

    DiscountDto findDiscount(Long id);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    TokenResponseDto loginAdmin(TokenRequestDto tokenRequestDto);

    UserDto addUser(UserCreateDto userCreateDto);

    CardDto addCard(CreateCardDto createCardDto);

    UserDto update(UserDto userDto);

    void incrementMiles(IncrementMilesDto incrementMilesDto);

    void cancelTicket(TicketCancelDto ticketCancelDto);
}
