package com.accenture.im.entity;

import java.time.LocalDateTime;

public class AuthEntity {
    private String loginName;
    private String loginPassword;
    private String name;
    private Integer loginFailedCount;
    private String accountLockFlag;
    private String salt;
    private LocalDateTime accountLockDate;
    private LocalDateTime passwordLastUpdatedDate;

    public AuthEntity() {
        super();
    }

    public AuthEntity(String loginName) {
        super();
        this.loginName = loginName;
    }

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

    public Integer getLoginFailedCount() {
        return loginFailedCount;
    }

    public void setLoginFailedCount(Integer loginFailedCount) {
        this.loginFailedCount = loginFailedCount;
    }

    public String getAccountLockFlag() {
        return accountLockFlag;
    }

    public void setAccountLockFlag(String accountLockFlag) {
        this.accountLockFlag = accountLockFlag;
    }

    public LocalDateTime getAccountLockDate() {
        return accountLockDate;
    }

    public void setAccountLockDate(LocalDateTime accountLockDate) {
        this.accountLockDate = accountLockDate;
    }

    public LocalDateTime getPasswordLastUpdatedDate() {
        return passwordLastUpdatedDate;
    }

    public void setPasswordLastUpdatedDate(LocalDateTime passwordLastUpdatedDate) {
        this.passwordLastUpdatedDate = passwordLastUpdatedDate;
    }

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
