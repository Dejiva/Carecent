package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class CPAttrValueDto implements Serializable {

	private static final long serialVersionUID = 3590059579841110390L;
	
	private String carePathAttrCode;
	private String carePathAttrName;
	private String value;
	private boolean metCondition;
	private String minValue;
	private String maxValue;
	private String dataType;
	

	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public boolean isMetCondition() {
		return metCondition;
	}
	public void setMetCondition(boolean metCondition) {
		this.metCondition = metCondition;
	}
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
}
