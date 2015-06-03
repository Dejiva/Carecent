package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class AdmissionFormAttribNdValuesDto implements Serializable {
	private static final long serialVersionUID = 6494214672126275024L;
	
	private Long attribId;
	private String attibValue;
	private String attibute;
	private String type;
	
	public Long getAttribId() {
		return attribId;
	}
	public void setAttribId(Long attribId) {
		this.attribId = attribId;
	}
	public String getAttibValue() {
		return attibValue;
	}
	public void setAttibValue(String attibValue) {
		this.attibValue = attibValue;
	}
	public String getAttibute() {
		return attibute;
	}
	public void setAttibute(String attibute) {
		this.attibute = attibute;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
