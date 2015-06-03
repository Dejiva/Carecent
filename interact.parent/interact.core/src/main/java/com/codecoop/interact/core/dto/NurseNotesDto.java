package com.codecoop.interact.core.dto;


import org.springframework.stereotype.Component;


@Component
public class NurseNotesDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7094404334390022859L;
	private String createdDate;
	private Long sbarNotesId;
	private String notes;
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Long getSbarNotesId() {
		return sbarNotesId;
	}
	public void setSbarNotesId(Long sbarNotesId) {
		this.sbarNotesId = sbarNotesId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	
	
}
