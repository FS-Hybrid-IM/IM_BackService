package com.accenture.im.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class TeleWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //webSocket连接使用url
        stompEndpointRegistry.addEndpoint("/teleconference/getWebSocketServer").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //向客户端发送信息时，destination的前缀必须是配置中的topic和teleconference
        config.enableSimpleBroker("/topic","/teleconference");
        //客户端向服务端发起请求时，必须以tele为前缀
        config.setApplicationDestinationPrefixes("/tele");
        //服务器向客户端发起一对一信息时，必须以teleconference为前缀
        config.setUserDestinationPrefix("/teleconference/");
    }
}