package com.accenture.im.repository;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.accenture.im.entity.AuthEntity;

@Repository
public interface AuthRepository {

	AuthEntity selectByLoginName(@Param("loginName") String loginName);

	int insert(AuthEntity entity);

	int update(AuthEntity entity);

//    int releaseLock(@Param("userMngNo") String userMngNo, @Param("updatedUserId") String updateUserId,
//            @Param("updatedDate") LocalDateTime updateDate);
//
//    int clearLockCount(@Param("userMngNo") String userMngNo, @Param("updatedUserId") String updateUserId,
//            @Param("updatedDate") LocalDateTime updateDate);
//
//    int updateLoginFailureCount(@Param("userMngNo") String userMngNo, @Param("updatedUserId") String updateUserId,
//            @Param("updatedDate") LocalDateTime updateDate);
//
//    int lockAccount(@Param("userMngNo") String userMngNo, @Param("accountLockDate") LocalDateTime accountLockDate,
//            @Param("updatedUserId") String updateUserId, @Param("updatedDate") LocalDateTime updateDate);
}
