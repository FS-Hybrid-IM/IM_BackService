package com.accenture.im.web.controller;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.im.web.service.TeleWebSocketManager;

@Controller
public class TeleWebSocketController {
    @Resource
    private TeleWebSocketManager teleWebSocketManager;

    @SubscribeMapping("/teleStatus")
    public String getStatus(@RequestParam String msg) {
        return msg;
    }

    @MessageMapping("/init")
    public String init(@RequestParam String msg) {
        return msg;
    }
}