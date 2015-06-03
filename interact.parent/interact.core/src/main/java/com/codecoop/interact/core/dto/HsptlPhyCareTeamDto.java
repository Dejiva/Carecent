package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class HsptlPhyCareTeamDto implements Serializable {
	private String staffName;
	private String specialityName;
	private Long SpecialityId;
	private String contactno;
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getSpecialityName() {
		return specialityName;
	}
	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}
	public Long getSpecialityId() {
		return SpecialityId;
	}
	public void setSpecialityId(Long specialityId) {
		SpecialityId = specialityId;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	
	

}
