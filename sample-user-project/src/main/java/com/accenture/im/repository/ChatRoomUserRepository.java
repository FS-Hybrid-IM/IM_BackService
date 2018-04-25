package com.accenture.im.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accenture.im.entity.ChatRoomUserEntity;

@Repository
public interface ChatRoomUserRepository {
    List<ChatRoomUserEntity> selectByChatRoomId(int chatRoomId);
    void insert(ChatRoomUserEntity entity);
    void update(ChatRoomUserEntity entity);
    void delete(int chartRoomId);
}
