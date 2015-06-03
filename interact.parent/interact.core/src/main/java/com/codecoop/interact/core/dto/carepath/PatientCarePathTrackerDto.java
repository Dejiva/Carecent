package com.codecoop.interact.core.dto.carepath;

import com.codecoop.interact.core.domain.CarePath;
import com.codecoop.interact.core.domain.PatientEpisode;

public class PatientCarePathTrackerDto {

    
    private String carePathAttrCode;
    private String carePathAttrName;
    private PatientEpisode patientEpisode;
	private CarePath carePath;
	
	private Integer stepId;
	private String value;
	private Boolean notifyDoctorFlag;
	public String getCarePathAttrCode() {
		return carePathAttrCode;
	}
	public void setCarePathAttrCode(String carePathAttrCode) {
		this.carePathAttrCode = carePathAttrCode;
	}
	public String getCarePathAttrName() {
		return carePathAttrName;
	}
	public void setCarePathAttrName(String carePathAttrName) {
		this.carePathAttrName = carePathAttrName;
	}
	public PatientEpisode getPatientEpisode() {
		return patientEpisode;
	}
	public void setPatientEpisode(PatientEpisode patientEpisode) {
		this.patientEpisode = patientEpisode;
	}
	public CarePath getCarePath() {
		return carePath;
	}
	public void setCarePath(CarePath carePath) {
		this.carePath = carePath;
	}
	public Integer getStepId() {
		return stepId;
	}
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getNotifyDoctorFlag() {
		return notifyDoctorFlag;
	}
	public void setNotifyDoctorFlag(Boolean notifyDoctorFlag) {
		this.notifyDoctorFlag = notifyDoctorFlag;
	}
    
}
