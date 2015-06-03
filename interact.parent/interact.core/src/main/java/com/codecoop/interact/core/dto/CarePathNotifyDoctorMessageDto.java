package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class CarePathNotifyDoctorMessageDto implements Serializable{

	private static final long serialVersionUID = -340849948964355705L;
	
	private String carePathName;
	private String stepName;
	private String carePathAttrName;
	
	public String  getCarePathName() {
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
	public String getCarePathAttrName() {
		return carePathAttrName;
	}
	public void setCarePathAttrName(String carePathAttrName) {
		this.carePathAttrName = carePathAttrName;
	}
	
}
