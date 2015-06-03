package com.codecoop.interact.core.model;

import java.io.Serializable;

public class PatientDetailsModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long patientEpisodeId;
	private String fullName;
	private boolean stopAndWatchEligible;
	private Long stpwCount;
	private boolean hasResponse;
	private boolean admissionQueue;
	private boolean stopAndWathQueue;
	private boolean observationQueue;
	private boolean acutecareQueue;
	private boolean stayedInHosp;
	private Long admissionQueueCount;
	private Long stopAndWathQueueCount;
	private Long observationQueueCount;
	private Long acutecareQueueCount;
	
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
	public boolean isStopAndWatchEligible() {
		return stopAndWatchEligible;
	}
	public void setStopAndWatchEligible(boolean stopAndWatchEligible) {
		this.stopAndWatchEligible = stopAndWatchEligible;
	}
	public Long getStpwCount() {
		return stpwCount;
	}
	public void setStpwCount(Long stpwCount) {
		this.stpwCount = stpwCount;
	}
	public boolean isHasResponse() {
		return hasResponse;
	}
	public void setHasResponse(boolean hasResponse) {
		this.hasResponse = hasResponse;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public boolean isAdmissionQueue() {
		return admissionQueue;
	}
	public void setAdmissionQueue(boolean admissionQueue) {
		this.admissionQueue = admissionQueue;
	}
	public boolean isStopAndWathQueue() {
		return stopAndWathQueue;
	}
	public void setStopAndWathQueue(boolean stopAndWathQueue) {
		this.stopAndWathQueue = stopAndWathQueue;
	}
	public boolean isObservationQueue() {
		return observationQueue;
	}
	public void setObservationQueue(boolean observationQueue) {
		this.observationQueue = observationQueue;
	}
	public boolean isAcutecareQueue() {
		return acutecareQueue;
	}
	public void setAcutecareQueue(boolean acutecareQueue) {
		this.acutecareQueue = acutecareQueue;
	}
	public Long getAdmissionQueueCount() {
		return admissionQueueCount;
	}
	public void setAdmissionQueueCount(Long admissionQueueCount) {
		this.admissionQueueCount = admissionQueueCount;
	}
	public Long getStopAndWathQueueCount() {
		return stopAndWathQueueCount;
	}
	public void setStopAndWathQueueCount(Long stopAndWathQueueCount) {
		this.stopAndWathQueueCount = stopAndWathQueueCount;
	}
	public Long getObservationQueueCount() {
		return observationQueueCount;
	}
	public void setObservationQueueCount(Long observationQueueCount) {
		this.observationQueueCount = observationQueueCount;
	}
	public Long getAcutecareQueueCount() {
		return acutecareQueueCount;
	}
	public void setAcutecareQueueCount(Long acutecareQueueCount) {
		this.acutecareQueueCount = acutecareQueueCount;
	}
	public boolean isStayedInHosp() {
		return stayedInHosp;
	}
	public void setStayedInHosp(boolean stayedInHosp) {
		this.stayedInHosp = stayedInHosp;
	}
}
