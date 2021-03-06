package com.accenture.im.repository;

import org.springframework.stereotype.Repository;

import com.accenture.im.entity.DeviceEntity;

@Repository
public interface DeviceRepository {
    DeviceEntity selectByLoginName(String loginName);
    DeviceEntity selectToken(String token);
    void insert(DeviceEntity entity);
    void update(DeviceEntity entity);
}
