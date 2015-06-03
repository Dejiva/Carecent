package com.codecoop.interact.core.dto;

public class StaffDto {
	private Long staffId;
	private String staffName;
	private String alertTemplate;
	private String workEmail;
	private Long reportedToDoctor;
	private Boolean assignedFlag;
	private String deviceToken;
	private String deviceType;
	private Boolean deviceLoggedStatus;
	public StaffDto(){
		
	}
public StaffDto(Long staffId,String alertTemplate,String workEmail,Long reportedToDoctor){
		this.staffId=staffId;
		this.alertTemplate=alertTemplate;
		this.workEmail=workEmail;
		this.reportedToDoctor=reportedToDoctor;
	}
	public String getStaffName() {
	return staffName;
}
public void setStaffName(String staffName) {
	this.staffName = staffName;
}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public String getAlertTemplate() {
		return alertTemplate;
	}
	public void setAlertTemplate(String alertTemplate) {
		this.alertTemplate = alertTemplate;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	public Long getReportedToDoctor() {
		return reportedToDoctor;
	}
	public void setReportedToDoctor(Long reportedToDoctor) {
		this.reportedToDoctor = reportedToDoctor;
	}
	public Boolean getAssignedFlag() {
		return assignedFlag;
	}
	public void setAssignedFlag(Boolean assignedFlag) {
		this.assignedFlag = assignedFlag;
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
