package com.accenture.im.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.accenture.im.entity.ChatRoomEntity;

@Mapper
public interface ChatRoomRepository {
    List<ChatRoomEntity> selectByLoginName(String loginName);
    void insert(ChatRoomEntity entity);
    void update(ChatRoomEntity entity);
    void delete(int chartRoomId);
    ChatRoomEntity selectByLoginNameAndSortNum(@Param("loginName") String loginName,@Param("sortNum") int sortNum);
}
