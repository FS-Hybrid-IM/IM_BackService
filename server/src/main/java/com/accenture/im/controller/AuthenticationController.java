package com.accenture.im.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.accenture.im.entity.AuthEntity;
import com.accenture.im.entity.AuthTokenEntity;
import com.accenture.im.repository.AuthRepository;
import com.accenture.im.requestdto.LoginRequestForm;

@Path("/auth/login")
public class AuthenticationController {

    @Autowired
    AuthRepository authRepository;

    @POST()
    public AuthTokenEntity login(LoginRequestForm loginReq, Errors errors) {
    	AuthEntity authEntity = authRepository.selectByLoginName(loginReq.getLoginName());
        return new AuthTokenEntity();
    }
}
