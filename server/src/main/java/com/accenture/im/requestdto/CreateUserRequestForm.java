package com.accenture.im.requestdto;



public class CreateUserRequestForm {

    private String loginName;
    private String name;
    private String loginPassword;
    private String nextLoginPassword;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNextLoginPassword() {
		return nextLoginPassword;
	}

	public void setNextLoginPassword(String nextLoginPassword) {
		this.nextLoginPassword = nextLoginPassword;
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

}
