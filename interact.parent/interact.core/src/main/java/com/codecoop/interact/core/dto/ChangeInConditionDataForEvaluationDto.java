package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class ChangeInConditionDataForEvaluationDto implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4340833161473247762L;

	private String signsSymptomsLabworkName;
	
	private String changeInConditionValue;
	
	private String maxVal;
	private String minVal;
	private String datatype;
	private Boolean immediateFlag;
	private Boolean signFlag;
	private Boolean symptomFlag;
	private Boolean labworkFlag;
	public String getSignsSymptomsLabworkName() {
		return signsSymptomsLabworkName;
	}
	public void setSignsSymptomsLabworkName(String signsSymptomsLabworkName) {
		this.signsSymptomsLabworkName = signsSymptomsLabworkName;
	}
	public String getChangeInConditionValue() {
		return changeInConditionValue;
	}
	public void setChangeInConditionValue(String changeInConditionValue) {
		this.changeInConditionValue = changeInConditionValue;
	}
	
	public String getMaxVal() {
		return maxVal;
	}
	public void setMaxVal(String maxVal) {
		this.maxVal = maxVal;
	}
	public String getMinVal() {
		return minVal;
	}
	public void setMinVal(String minVal) {
		this.minVal = minVal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public Boolean getImmediateFlag() {
		return immediateFlag;
	}
	public void setImmediateFlag(Boolean immediateFlag) {
		this.immediateFlag = immediateFlag;
	}
	public Boolean getSignFlag() {
		return signFlag;
	}
	public void setSignFlag(Boolean signFlag) {
		this.signFlag = signFlag;
	}
	public Boolean getSymptomFlag() {
		return symptomFlag;
	}
	public void setSymptomFlag(Boolean symptomFlag) {
		this.symptomFlag = symptomFlag;
	}
	public Boolean getLabworkFlag() {
		return labworkFlag;
	}
	public void setLabworkFlag(Boolean labworkFlag) {
		this.labworkFlag = labworkFlag;
	}

	 
}
