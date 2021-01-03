package com.a2.userservice.listener;

import com.a2.userservice.dto.TicketCancelDto;
import com.a2.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.Message;
import javax.jms.TextMessage;

public class IncrementMilesListener {

    private ObjectMapper objectMapper;
    private UserService userService;

    public IncrementMilesListener(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @JmsListener(destination = "${destination.increment-miles}", concurrency = "5-10")
    public void handleCancelMiles(Message message){
        try {
            String jsonText = ((TextMessage)message).getText();
            TicketCancelDto ticketCancelDto = objectMapper.readValue(jsonText, TicketCancelDto.class);
            userService.cancelTicket(ticketCancelDto);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
}
