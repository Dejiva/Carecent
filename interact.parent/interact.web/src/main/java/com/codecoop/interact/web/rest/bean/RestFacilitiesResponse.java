package com.codecoop.interact.web.rest.bean;

public class RestFacilitiesResponse {
	
	private Long fStaffId;
	private Long facilityId;
	private String facilityName;
	
	public RestFacilitiesResponse() {
	}
	
	public RestFacilitiesResponse(Long fStaffId, Long facilityId,
			String facilityName) {
		super();
		this.fStaffId = fStaffId;
		this.facilityId = facilityId;
		this.facilityName = facilityName;
	}

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
	
	

}
