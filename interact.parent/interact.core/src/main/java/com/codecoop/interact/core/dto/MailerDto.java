package com.codecoop.interact.core.dto;



public class MailerDto implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1232939145919081074L;
	
	private String staffName;
	private String role;
	

	public MailerDto(){}


	public MailerDto(String staffName, String role) {
		super();
		this.staffName = staffName;
		this.role = role;
	}


	public String getStaffName() {
		return staffName;
	}


	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
 
	
	}
