package com.codecoop.interact.core.rest.dto;

import java.io.Serializable;
import java.util.Date;

public class RestNotesDto implements Serializable {
	private static final long serialVersionUID = -7532140064637122072L;
	
	private Long id;
	private Long sbarId;
	private String notes;
	private String staffType;
	private Long fStaffId;
	private String modifiedBy;
	private Date date;
	
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public Long getfStaffId() {
		return fStaffId;
	}
	public void setfStaffId(Long fStaffId) {
		this.fStaffId = fStaffId;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
