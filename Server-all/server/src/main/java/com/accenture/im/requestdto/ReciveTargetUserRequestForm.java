package com.accenture.im.requestdto;

import javax.validation.constraints.NotNull;

public class ReciveTargetUserRequestForm {

    @NotNull
    private Integer chatRoomId;

	public Integer getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
	}

}
