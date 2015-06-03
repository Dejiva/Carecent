package com.codecoop.interact.core.dto;

public class SignsSymptomsLabworkDto {
	
	private Long id;
	private String signsSymptomsLabworkName;
	private Boolean signFlag;
	private Boolean symptomFlag;
	private Boolean labworkFlag;
	private String refCarePathCode;
	private String carepathLabworkCode;
	
	public String getRefCarePathCode() {
		return refCarePathCode;
	}
	public void setRefCarePathCode(String refCarePathCode) {
		this.refCarePathCode = refCarePathCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSignsSymptomsLabworkName() {
		return signsSymptomsLabworkName;
	}
	public void setSignsSymptomsLabworkName(String signsSymptomsLabworkName) {
		this.signsSymptomsLabworkName = signsSymptomsLabworkName;
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
	public String getCarepathLabworkCode() {
		return carepathLabworkCode;
	}
	public void setCarepathLabworkCode(String carepathLabworkCode) {
		this.carepathLabworkCode = carepathLabworkCode;
	}

}
