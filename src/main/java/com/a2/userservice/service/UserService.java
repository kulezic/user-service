package com.a2.userservice.service;

import com.a2.userservice.dto.DiscountDto;
import com.a2.userservice.dto.TicketCancelDto;
import com.a2.userservice.dto.TokenRequestDto;
import com.a2.userservice.dto.TokenResponseDto;

public interface UserService {

    DiscountDto findDiscount(Long id);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    void cancelTicket(TicketCancelDto ticketCancelDto);
}
