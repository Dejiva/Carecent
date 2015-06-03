package com.codecoop.interact.core.rest.dto;

public class RestPractitionerNotesDto {
	
	private Long patientEpisodeId;
	private Long fStaffId;
//	private Long nursingStaffId;
//	private String nursingStaffType;
//	private Long facilityId;
	private String notes;
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	public Long getfStaffId() {
		return fStaffId;
	}
	public void setfStaffId(Long fStaffId) {
		this.fStaffId = fStaffId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
