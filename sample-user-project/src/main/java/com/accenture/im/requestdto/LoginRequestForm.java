package com.accenture.im.requestdto;

import javax.validation.constraints.NotBlank;


public class LoginRequestForm {

    @NotBlank
    private String loginName;
    @NotBlank
    private String loginPassword;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

}
