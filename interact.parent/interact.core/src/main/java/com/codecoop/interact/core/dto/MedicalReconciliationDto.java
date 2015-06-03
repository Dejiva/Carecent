package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class MedicalReconciliationDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3776209941757113951L; 
	
	private Long medicalReconciliationId;
	private Long patientEncounterId;
	private Long recommendationId;
	private String recommendation;
	private String medicationDetails;
	private String clarificationNeeded;
	private String comments;
	private String resolution;
	private Long resolutionId;
	private Boolean tobeDeleted;
	public Long getMedicalReconciliationId() {
		return medicalReconciliationId;
	}
	public void setMedicalReconciliationId(Long medicalReconciliationId) {
		this.medicalReconciliationId = medicalReconciliationId;
	}
	public Long getPatientEncounterId() {
		return patientEncounterId;
	}
	public void setPatientEncounterId(Long patientEncounterId) {
		this.patientEncounterId = patientEncounterId;
	}
	public Long getRecommendationId() {
		return recommendationId;
	}
	public void setRecommendationId(Long recommendationId) {
		this.recommendationId = recommendationId;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public String getMedicationDetails() {
		return medicationDetails;
	}
	public void setMedicationDetails(String medicationDetails) {
		this.medicationDetails = medicationDetails;
	}
	public String getClarificationNeeded() {
		return clarificationNeeded;
	}
	public void setClarificationNeeded(String clarificationNeeded) {
		this.clarificationNeeded = clarificationNeeded;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public Long getResolutionId() {
		return resolutionId;
	}
	public void setResolutionId(Long resolutionId) {
		this.resolutionId = resolutionId;
	}
	public Boolean getTobeDeleted() {
		return tobeDeleted;
	}
	public void setTobeDeleted(Boolean tobeDeleted) {
		this.tobeDeleted = tobeDeleted;
	}
	
	
}
