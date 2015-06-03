package com.codecoop.interact.core.dto;


import org.springframework.stereotype.Component;


@Component
public class SbarNotesDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4944585684601184770L;
	private Long patientEpisodeId;
	private Long nursingStaffId;
	private String nursingStaffType;
	private Long facilityId;
	private String notes;
	


	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}

	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}

	public Long getNursingStaffId() {
		return nursingStaffId;
	}

	public void setNursingStaffId(Long nursingStaffId) {
		this.nursingStaffId = nursingStaffId;
	}

	public String getNursingStaffType() {
		return nursingStaffType;
	}

	public void setNursingStaffType(String nursingStaffType) {
		this.nursingStaffType = nursingStaffType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
