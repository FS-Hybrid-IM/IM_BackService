package com.accenture.im.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.accenture.im.entity.ChatRoomUserEntity;

@Mapper
public interface ChatRoomUserRepository {
    List<ChatRoomUserEntity> selectByChatRoomId(int chatRoomId);
    void insert(ChatRoomUserEntity entity);
    void update(ChatRoomUserEntity entity);
    void delete(int chartRoomId);
}
