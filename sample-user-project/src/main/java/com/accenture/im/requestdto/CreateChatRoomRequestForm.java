package com.accenture.im.requestdto;

import org.hibernate.validator.constraints.NotBlank;

public class CreateChatRoomRequestForm {

    @NotBlank
    private String loginName;
    @NotBlank
    private String targetLoginName;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getTargetLoginName() {
		return targetLoginName;
	}
	public void setTargetLoginName(String targetLoginName) {
		this.targetLoginName = targetLoginName;
	}
}
