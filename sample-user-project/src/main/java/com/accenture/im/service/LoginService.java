package com.accenture.im.service;

import com.accenture.im.entity.AuthTokenEntity;
import com.accenture.im.requestdto.CreateUserRequestForm;
import com.accenture.im.requestdto.LoginRequestForm;

public interface LoginService {

    AuthTokenEntity login(LoginRequestForm loginReq);

    void createUser(CreateUserRequestForm loginReq);
}