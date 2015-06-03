package com.codecoop.interact.web.rest.bean;

public class RestPatient {
	
	private Long id;
	private Long patientEpisodeId;
	private String fullName;
	
	public RestPatient() {
	}
	public RestPatient(Long id, Long patientEpisodeId, String fullName) {
		super();
		this.id = id;
		this.patientEpisodeId = patientEpisodeId;
		this.fullName = fullName;
	}
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
