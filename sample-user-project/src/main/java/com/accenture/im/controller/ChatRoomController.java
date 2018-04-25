package com.accenture.im.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.im.entity.ChatRoomUserEntity;
import com.accenture.im.exception.BusinessFailureException;
import com.accenture.im.requestdto.CreateChatRoomRequestForm;
import com.accenture.im.requestdto.ReciveTargetUserRequestForm;
import com.accenture.im.service.ChatRoomService;

@RestController
@RequestMapping(value = "/chat-room", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public void createChatRoom(@RequestBody @Valid CreateChatRoomRequestForm req, Errors errors) {
        if (errors.hasErrors()) {
            throw new BusinessFailureException(errors);
        }
        chatRoomService.createChatRoom(req);
    }

    @RequestMapping(value = "/receive/targetUser", method = {RequestMethod.GET})
    public List<ChatRoomUserEntity> receiveTargetUser(@RequestBody @Valid ReciveTargetUserRequestForm req, Errors errors) {
        if (errors.hasErrors()) {
            throw new BusinessFailureException(errors);
        }
        return chatRoomService.receiveTargetUser(req);
    }
}
