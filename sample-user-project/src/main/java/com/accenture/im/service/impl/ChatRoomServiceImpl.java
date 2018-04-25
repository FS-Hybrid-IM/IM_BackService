package com.accenture.im.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.accenture.im.entity.AuthEntity;
import com.accenture.im.entity.ChatRoomEntity;
import com.accenture.im.entity.ChatRoomUserEntity;
import com.accenture.im.exception.BusinessFailureException;
import com.accenture.im.repository.AuthRepository;
import com.accenture.im.repository.ChatRoomRepository;
import com.accenture.im.repository.ChatRoomUserRepository;
import com.accenture.im.requestdto.CreateChatRoomRequestForm;
import com.accenture.im.requestdto.ReciveTargetUserRequestForm;
import com.accenture.im.service.ChatRoomService;

@Service
@Primary
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatRoomUserRepository chatRoomUserRepository;

    public void createChatRoom(CreateChatRoomRequestForm req) {
        final String loginName = req.getLoginName();
        final String targetLoginName = req.getTargetLoginName();

        AuthEntity loginEntity = authRepository.selectByLoginName(loginName);
        AuthEntity targetEntity = authRepository.selectByLoginName(targetLoginName);
        if (targetEntity == null) {
            throw new BusinessFailureException("Target login name is not have.");
        }
        List<ChatRoomEntity> loginList = chatRoomRepository.selectByLoginName(loginName);
        ChatRoomEntity loginChatRoomEntity = new ChatRoomEntity();
        loginChatRoomEntity.setLoginName(loginName);
        loginChatRoomEntity.setChatRoomName(targetEntity.getName() + "'s chat room");
        loginChatRoomEntity.setChatRoomTitle(targetEntity.getName() + "'s chat room");
        loginChatRoomEntity.setSortNum(loginList.size());
        chatRoomRepository.insert(loginChatRoomEntity);
        List<ChatRoomEntity> targetList = chatRoomRepository.selectByLoginName(targetLoginName);
        ChatRoomEntity targetChatRoomEntity = new ChatRoomEntity();
        targetChatRoomEntity.setLoginName(targetLoginName);
        targetChatRoomEntity.setChatRoomName(loginEntity.getName() + "'s chat room");
        targetChatRoomEntity.setChatRoomTitle(loginEntity.getName() + "'s chat room");
        targetChatRoomEntity.setSortNum(targetList.size());
        chatRoomRepository.insert(targetChatRoomEntity);
        ChatRoomEntity newChatRoomEntity = chatRoomRepository.selectByLoginNameAndSortNum(loginName, loginList.size());
        ChatRoomUserEntity loginChatRoom = new ChatRoomUserEntity();
        loginChatRoom.setChatRoomId(newChatRoomEntity.getChatRoomId());
        loginChatRoom.setChatRoomLoginName(targetLoginName);
        chatRoomUserRepository.insert(loginChatRoom);
        ChatRoomEntity newChatRoomEntity2 = chatRoomRepository.selectByLoginNameAndSortNum(targetLoginName, targetList.size());
        ChatRoomUserEntity targetChatRoom = new ChatRoomUserEntity();
        targetChatRoom.setChatRoomId(newChatRoomEntity2.getChatRoomId());
        targetChatRoom.setChatRoomLoginName(loginName);
        chatRoomUserRepository.insert(targetChatRoom);
    }

    public List<ChatRoomUserEntity> receiveTargetUser(ReciveTargetUserRequestForm req) {
    	return chatRoomUserRepository.selectByChatRoomId(req.getChatRoomId());
    }
}
