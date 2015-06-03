package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class PatientInfoDto implements Serializable {
	private Long patientId;
	private String patientName;
	private Long patientEpisodeId;
	private Long patientEncounterId;
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	public Long getPatientEncounterId() {
		return patientEncounterId;
	}
	public void setPatientEncounterId(Long patientEncounterId) {
		this.patientEncounterId = patientEncounterId;
	}
}
