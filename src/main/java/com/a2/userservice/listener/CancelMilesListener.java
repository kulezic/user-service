package com.a2.userservice.listener;

import com.a2.userservice.dto.CancelMilesDto;
import com.a2.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class CancelMilesListener {

    private ObjectMapper objectMapper;
    private UserService userService;

    public CancelMilesListener(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @JmsListener(destination = "${destination.cancel-miles}", concurrency = "5-10")
    public void handleCancelMiles(Message message){
        try {
            String jsonText = ((TextMessage)message).getText();
            CancelMilesDto cancelMilesDto = objectMapper.readValue(jsonText, CancelMilesDto.class);
            userService.cancelMiles(cancelMilesDto);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
}
