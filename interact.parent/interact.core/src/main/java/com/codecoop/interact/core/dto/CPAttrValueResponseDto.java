package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class CPAttrValueResponseDto implements Serializable {

	private static final long serialVersionUID = 3590059579841110390L;
	
	private String carePathAttrCode;
	private String carePathAttrName;
	private String value;
	private boolean notifyDoctorFlag;
	private String minVal;
	private String maxVal;
	private String dataType;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isNotifyDoctorFlag() {
		return notifyDoctorFlag;
	}
	public void setNotifyDoctorFlag(boolean notifyDoctorFlag) {
		this.notifyDoctorFlag = notifyDoctorFlag;
	}
	public String getMinVal() {
		return minVal;
	}
	public void setMinVal(String minVal) {
		this.minVal = minVal;
	}
	public String getMaxVal() {
		return maxVal;
	}
	public void setMaxVal(String maxVal) {
		this.maxVal = maxVal;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	

}
