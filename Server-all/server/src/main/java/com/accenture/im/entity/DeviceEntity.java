package com.accenture.im.entity;

import java.time.LocalDateTime;

public class DeviceEntity {
    private String loginName;
    private String deviceId;
    private String authToken;
    private LocalDateTime authTokenPublishDate;
    private LocalDateTime lastSyncDate;
    private String pushDeviceToken;
    private String necessityAuthSecretQuestion;

    public DeviceEntity() {
        super();
    }

    public DeviceEntity(String loginName) {
        this.setLoginName(loginName);
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public LocalDateTime getAuthTokenPublishDate() {
        return authTokenPublishDate;
    }

    public void setAuthTokenPublishDate(LocalDateTime authTokenPublishDate) {
        this.authTokenPublishDate = authTokenPublishDate;
    }

    public LocalDateTime getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(LocalDateTime lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public String getPushDeviceToken() {
        return pushDeviceToken;
    }

    public void setPushDeviceToken(String pushDeviceToken) {
        this.pushDeviceToken = pushDeviceToken;
    }

    public String getNecessityAuthSecretQuestion() {
        return necessityAuthSecretQuestion;
    }

    public void setNecessityAuthSecretQuestion(String necessityAuthSecretQuestion) {
        this.necessityAuthSecretQuestion = necessityAuthSecretQuestion;
    }

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
