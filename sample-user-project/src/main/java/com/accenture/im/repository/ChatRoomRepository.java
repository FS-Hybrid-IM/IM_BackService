package com.accenture.im.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.accenture.im.entity.ChatRoomEntity;

@Repository
public interface ChatRoomRepository {
    List<ChatRoomEntity> selectByLoginName(String loginName);
    void insert(ChatRoomEntity entity);
    void update(ChatRoomEntity entity);
    void delete(int chartRoomId);
    ChatRoomEntity selectByLoginNameAndSortNum(@Param("loginName") String loginName,@Param("sortNum") int sortNum);
}
