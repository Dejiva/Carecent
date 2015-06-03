package com.codecoop.interact.web.rest.bean;

public class RestUpdateLabOrdersRequest {

	private Long id;
	private Long sbarId;
	private Long signsSymptomsLabworkId;
	private Boolean approved;
	private Boolean rejected;
	private Long facilityStaffId;
	private String signSymtomLabworkName;
	private Boolean stndingOrder;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSbarId() {
		return sbarId;
	}
	public void setSbarId(Long sbarId) {
		this.sbarId = sbarId;
	}
	public Long getSignsSymptomsLabworkId() {
		return signsSymptomsLabworkId;
	}
	public void setSignsSymptomsLabworkId(Long signsSymptomsLabworkId) {
		this.signsSymptomsLabworkId = signsSymptomsLabworkId;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	public Boolean getRejected() {
		return rejected;
	}
	public void setRejected(Boolean rejected) {
		this.rejected = rejected;
	}
	public Long getFacilityStaffId() {
		return facilityStaffId;
	}
	public void setFacilityStaffId(Long facilityStaffId) {
		this.facilityStaffId = facilityStaffId;
	}
	public String getSignSymtomLabworkName() {
		return signSymtomLabworkName;
	}
	public void setSignSymtomLabworkName(String signSymtomLabworkName) {
		this.signSymtomLabworkName = signSymtomLabworkName;
	}
	public Boolean getStndingOrder() {
		return stndingOrder;
	}
	public void setStndingOrder(Boolean stndingOrder) {
		this.stndingOrder = stndingOrder;
	}

}
