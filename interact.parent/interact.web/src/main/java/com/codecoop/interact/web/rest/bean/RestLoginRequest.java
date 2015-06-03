package com.codecoop.interact.web.rest.bean;

public class RestLoginRequest {
	private String userName;
	private String passwd;
	private String deviceToken;
	private String deviceType;
	private Boolean deviceLoggedStatus;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public Boolean getDeviceLoggedStatus() {
		return deviceLoggedStatus;
	}
	public void setDeviceLoggedStatus(Boolean deviceLoggedStatus) {
		this.deviceLoggedStatus = deviceLoggedStatus;
	}

}
