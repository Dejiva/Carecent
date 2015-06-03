package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARE_PATH_LABWORKS", catalog = "INTERACT")
public class CarePathLabworks implements Serializable {
	private static final long serialVersionUID = 5835484903784189051L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name ="SBAR_ID")
	private Long sbarId;

	@Column(name = "SIGNS_SYMPTOMS_LABWORK_ID")
	private Long signsSymptomsLabworkId;

	@Column(name = "APPROVED")
	private Boolean approved;

	@Column(name = "REJECTED")
	private Boolean rejected;

	@Column(name = "FACILITY_STAFF_ID")
	private Long facilityStaffId;

	@Column(name = "CARE_PATH_CODE")
	private String carePathCode;
	
	@Column(name = "STANDING_ORDERS")
	private Boolean standingOrders; 
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getSbarId() {
		return sbarId;
	}

	public void setSbarId(Long sbarId) {
		this.sbarId = sbarId;
	}

}
