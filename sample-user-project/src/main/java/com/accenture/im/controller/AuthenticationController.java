package com.accenture.im.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.im.entity.AuthTokenEntity;
import com.accenture.im.exception.BusinessFailureException;
import com.accenture.im.requestdto.CreateUserRequestForm;
import com.accenture.im.requestdto.LoginRequestForm;
import com.accenture.im.service.LoginService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public abstract class AuthenticationController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public AuthTokenEntity login(@RequestBody @Valid LoginRequestForm loginReq, Errors errors) {
        if (errors.hasErrors()) {
            throw new BusinessFailureException(errors);
        }
        return loginService.login(loginReq);
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public void createUser(@RequestBody @Valid CreateUserRequestForm loginReq, Errors errors) {
    	if (errors.hasErrors()) {
            throw new BusinessFailureException(errors);
        }
    	loginService.createUser(loginReq);
    }

}
