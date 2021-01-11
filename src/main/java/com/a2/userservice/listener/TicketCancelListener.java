package com.a2.userservice.listener;

import com.a2.userservice.dto.IncrementMilesDto;
import com.a2.userservice.dto.TicketCancelDto;
import com.a2.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class TicketCancelListener {

    private ObjectMapper objectMapper;
    private UserService userService;

    public TicketCancelListener(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    //, concurrency = "5-10"
    @JmsListener(destination = "${destination.cancel-ticket}")
    public void handleCancelMiles(Message message){
        try {
            String jsonText = ((TextMessage)message).getText();
            IncrementMilesDto incrementMilesDto = objectMapper.readValue(jsonText, IncrementMilesDto.class);
            userService.incrementMiles(incrementMilesDto);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
}
