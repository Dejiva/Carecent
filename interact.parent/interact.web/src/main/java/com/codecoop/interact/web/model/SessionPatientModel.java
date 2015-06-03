package com.codecoop.interact.web.model;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.codecoop.interact.core.model.PatientDetailsModel;

public class SessionPatientModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<Long,PatientDetailsModel> patientDetailsModelMap;
	private Long admissionQueueSize;
	private Long stopNWatchQueueSize;
	private Long observationQueueSize;
	private Long acuteCareQueueSize;

	public LinkedHashMap<Long, PatientDetailsModel> getPatientDetailsModelMap() {
		return patientDetailsModelMap;
	}
	public void setPatientDetailsModelMap(
			LinkedHashMap<Long, PatientDetailsModel> patientDetailsModelMap) {
		this.patientDetailsModelMap = patientDetailsModelMap;
	}
	public Long getAdmissionQueueSize() {
		return admissionQueueSize;
	}
	public void setAdmissionQueueSize(Long admissionQueueSize) {
		this.admissionQueueSize = admissionQueueSize;
	}
	public Long getStopNWatchQueueSize() {
		return stopNWatchQueueSize;
	}
	public void setStopNWatchQueueSize(Long stopNWatchQueueSize) {
		this.stopNWatchQueueSize = stopNWatchQueueSize;
	}
	public Long getObservationQueueSize() {
		return observationQueueSize;
	}
	public void setObservationQueueSize(Long observationQueueSize) {
		this.observationQueueSize = observationQueueSize;
	}
	public Long getAcuteCareQueueSize() {
		return acuteCareQueueSize;
	}
	public void setAcuteCareQueueSize(Long acuteCareQueueSize) {
		this.acuteCareQueueSize = acuteCareQueueSize;
	}
	

}
