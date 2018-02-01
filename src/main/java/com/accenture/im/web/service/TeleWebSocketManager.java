package com.accenture.im.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class TeleWebSocketManager {
    private SimpMessageSendingOperations template;

    @Autowired
    public TeleWebSocketManager(SimpMessageSendingOperations template) {
        this.template = template;
    }

    public void sendToUser(String userName,String destination,Object payload){
        template.convertAndSendToUser(userName,destination,payload);
    }

    public void send(String destination,Object payload){
        template.convertAndSend(destination,payload);
    }
}