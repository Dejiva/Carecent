package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CarePathLabworksDto implements Serializable {
	private static final long serialVersionUID = 8810009457529746588L;

	private Long id;
	private Long sbarId;
	private Long patientEpisodeId;
	private Long signsSymptomsLabworkId;
	private Boolean approved;
	private Boolean rejected;
	private Long facilityStaffId;
	private String carePathCode;
	private Boolean standingOrders; 
	private Date startDate;
	private Date endDate;
	private String signSymtomLabworkName;
	private List<SignsSymptomsLabworkAttrDto> SignsSymptomsLabworkAttrs;
	private List<CarePathLabworkAppRejDto> carePathLabworkAppRej;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
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
	public String getCarePathCode() {
		return carePathCode;
	}
	public void setCarePathCode(String carePathCode) {
		this.carePathCode = carePathCode;
	}
	public Boolean getStandingOrders() {
		return standingOrders;
	}
	public void setStandingOrders(Boolean standingOrders) {
		this.standingOrders = standingOrders;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSignSymtomLabworkName() {
		return signSymtomLabworkName;
	}
	public void setSignSymtomLabworkName(String signSymtomLabworkName) {
		this.signSymtomLabworkName = signSymtomLabworkName;
	}
	public List<SignsSymptomsLabworkAttrDto> getSignsSymptomsLabworkAttrs() {
		return SignsSymptomsLabworkAttrs;
	}
	public void setSignsSymptomsLabworkAttrs(
			List<SignsSymptomsLabworkAttrDto> signsSymptomsLabworkAttrs) {
		SignsSymptomsLabworkAttrs = signsSymptomsLabworkAttrs;
	}
	public List<CarePathLabworkAppRejDto> getCarePathLabworkAppRej() {
		return carePathLabworkAppRej;
	}
	public void setCarePathLabworkAppRej(
			List<CarePathLabworkAppRejDto> carePathLabworkAppRej) {
		this.carePathLabworkAppRej = carePathLabworkAppRej;
	}
	public Long getSbarId() {
		return sbarId;
	}
	public void setSbarId(Long sbarId) {
		this.sbarId = sbarId;
	}
	
}
