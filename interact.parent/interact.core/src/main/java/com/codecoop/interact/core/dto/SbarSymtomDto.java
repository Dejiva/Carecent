package com.codecoop.interact.core.dto;


import org.springframework.stereotype.Component;


@Component
public class SbarSymtomDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4944585684601184770L;
	private String  Code;
	
	private Boolean  boolValue;
	private String textValue;
	private String dateValue;
	
	

	
	public Boolean getBoolValue() {
		return boolValue;
	}

	public void setBoolValue(Boolean boolValue) {
		this.boolValue = boolValue;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getTextValue() {
		return textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public String getDateValue() {
		return dateValue;
	}

	public void setDateValue(String dateValue) {
		this.dateValue = dateValue;
	}



	
	
}
