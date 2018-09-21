package com.accenture.im.repository;

import org.apache.ibatis.annotations.Mapper;

import com.accenture.im.entity.DeviceEntity;

@Mapper
public interface DeviceRepository {
    DeviceEntity selectByLoginName(String loginName);
    DeviceEntity selectToken(String token);
    void insert(DeviceEntity entity);
    void update(DeviceEntity entity);
}
