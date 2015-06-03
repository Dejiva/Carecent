package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarePathStepTrackerDto implements Serializable {

	private static final long serialVersionUID = 4906255556768954721L;
	private Long patientEpisodeId;
	private String currCarePathCode;
	private Integer stepId;
	private String stepName;
	private String stepType;
	private List<CPAttrValueDto> attrValues;
	private List<NextCarePathCode> nextCarePathCodes;
	private Integer nextStepId;
	private boolean notifyDoctorFlag;
	private int stepOrder;
	private boolean signOrLabWorkExists;
	private boolean labWorkExists;
	private boolean dataForSignOrLabWorkExists;	
	
	
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getStepType() {
		return stepType;
	}
	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	public int getStepOrder() {
		return stepOrder;
	}
	public void setStepOrder(int stepOrder) {
		this.stepOrder = stepOrder;
	}
	
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	public String getCurrCarePathCode() {
		return currCarePathCode;
	}
	public void setCurrCarePathCode(String currCarePathCode) {
		this.currCarePathCode = currCarePathCode;
	}
	public Integer getStepId() {
		return stepId;
	}
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
	public List<CPAttrValueDto> getAttrValues() {
		if(this.attrValues == null){
			this.attrValues = new ArrayList<CPAttrValueDto>();
		}
		return attrValues;
	}
	public void setAttrValues(List<CPAttrValueDto> attrValues) {
		this.attrValues = attrValues;
	}
	public List<NextCarePathCode> getNextCarePathCodes() {
		if(this.nextCarePathCodes == null){
			this.nextCarePathCodes = new ArrayList<NextCarePathCode>();
		}
		return nextCarePathCodes;
	}
	public void setNextCarePathCodes(List<NextCarePathCode> nextCarePathCodes) {
		this.nextCarePathCodes = nextCarePathCodes;
	}
	public boolean isNotifyDoctorFlag() {
		return notifyDoctorFlag;
	}
	public void setNotifyDoctorFlag(boolean notifyDoctorFlag) {
		this.notifyDoctorFlag = notifyDoctorFlag;
	}
	public Integer getNextStepId() {
		return nextStepId;
	}
	public void setNextStepId(Integer nextStepId) {
		this.nextStepId = nextStepId;
	}
	public boolean isSignOrLabWorkExists() {
		return signOrLabWorkExists;
	}
	public void setSignOrLabWorkExists(boolean signOrLabWorkExists) {
		this.signOrLabWorkExists = signOrLabWorkExists;
	}
	public boolean isDataForSignOrLabWorkExists() {
		return dataForSignOrLabWorkExists;
	}
	public void setDataForSignOrLabWorkExists(boolean dataForSignOrLabWorkExists) {
		this.dataForSignOrLabWorkExists = dataForSignOrLabWorkExists;
	}
	public boolean isLabWorkExists() {
		return labWorkExists;
	}
	public void setLabWorkExists(boolean labWorkExists) {
		this.labWorkExists = labWorkExists;
	}

}
