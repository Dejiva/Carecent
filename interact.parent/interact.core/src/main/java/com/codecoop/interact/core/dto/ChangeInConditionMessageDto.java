package com.codecoop.interact.core.dto;

import java.io.Serializable;
public class ChangeInConditionMessageDto implements Serializable{

	private static final long serialVersionUID = -340849948964355705L;
	
	private String signsSymptomsLabworkName;
	private String attrType;
	private String  message;
	public String getSignsSymptomsLabworkName() {
		return signsSymptomsLabworkName;
	}
	public void setSignsSymptomsLabworkName(String signsSymptomsLabworkName) {
		this.signsSymptomsLabworkName = signsSymptomsLabworkName;
	}
	public String getAttrType() {
		return attrType;
	}
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
