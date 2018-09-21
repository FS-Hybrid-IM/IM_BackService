package com.accenture.im.requestdto;


public class CreateChatRoomRequestForm {
    private String loginName;
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
