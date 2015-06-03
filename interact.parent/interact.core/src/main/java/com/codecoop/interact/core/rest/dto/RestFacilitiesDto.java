package com.codecoop.interact.core.rest.dto;

import java.io.Serializable;
import java.util.Date;

public class RestFacilitiesDto implements Serializable {
	private static final long serialVersionUID = -4678404221086613186L;
	private Long fStaffId;
	private Long facilityId;
	private String facilityName;
	private Date relievingDate;
	
	public Long getfStaffId() {
		return fStaffId;
	}
	public void setfStaffId(Long fStaffId) {
		this.fStaffId = fStaffId;
	}
	public Long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public Date getRelievingDate() {
		return relievingDate;
	}
	public void setRelievingDate(Date relievingDate) {
		this.relievingDate = relievingDate;
	}

}
