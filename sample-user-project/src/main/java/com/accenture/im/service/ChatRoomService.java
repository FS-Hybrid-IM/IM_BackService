package com.accenture.im.service;

import java.util.List;

import com.accenture.im.entity.ChatRoomUserEntity;
import com.accenture.im.requestdto.CreateChatRoomRequestForm;
import com.accenture.im.requestdto.ReciveTargetUserRequestForm;

public interface ChatRoomService {

    void createChatRoom(CreateChatRoomRequestForm req);
    List<ChatRoomUserEntity> receiveTargetUser(ReciveTargetUserRequestForm req);
}