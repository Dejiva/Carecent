package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarePathStepsResponseDto implements Serializable {

	private static final long serialVersionUID = 4906255556768954721L;
	
	private String carePathName;
	private Integer stepId;
	private String stepName;
	private String stepType;
	private List<CPAttrValueResponseDto> attrValues;
	
	
	public String getCarePathName() {
		return carePathName;
	}
	public void setCarePathName(String carePathName) {
		this.carePathName = carePathName;
	}
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
	public Integer getStepId() {
		return stepId;
	}
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
	public List<CPAttrValueResponseDto> getAttrValues() {
		return attrValues;
	}
	public void setAttrValues(List<CPAttrValueResponseDto> attrValues) {
		this.attrValues = attrValues;
	}
	

}
