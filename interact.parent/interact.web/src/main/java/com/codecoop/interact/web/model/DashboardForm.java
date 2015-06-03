package com.codecoop.interact.web.model;

import org.hibernate.validator.constraints.NotEmpty;

public class DashboardForm{
	@NotEmpty(message = "Must select a facility")
	private long facilityId;

	public long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(long facilityId) {
		this.facilityId = facilityId;
	}
	
	
}
