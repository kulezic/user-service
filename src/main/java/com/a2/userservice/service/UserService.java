package com.a2.userservice.service;

import com.a2.userservice.domain.Admin;
import com.a2.userservice.dto.*;

import java.util.List;

public interface UserService {

    DiscountDto findDiscount(Long id);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    TokenResponseDto loginAdmin(TokenRequestDto tokenRequestDto);

    UserDto addUser(UserCreateDto userCreateDto);

    CardDto addCard(CreateCardDto createCardDto);

    UserDto update(UserDto userDto);

    void incrementMiles(IncrementMilesDto incrementMilesDto);

    void cancelTicket(TicketCancelDto ticketCancelDto);

    List<Admin> getAdmin();

    UserDto getUser(Long id);

    UserRankDto findUserRank(Long id);

    List<CardDto> getUserCards(Long id);
}
