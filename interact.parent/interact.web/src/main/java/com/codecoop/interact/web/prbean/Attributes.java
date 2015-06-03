package com.codecoop.interact.web.prbean;

import java.io.Serializable;

public class Attributes implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -9082570051166037906L;
	
	private Long attributeId;
	private String attributeName;
	private String attributeValue;
	private String dataType;
	private String doctorNotification;
	private String units;
	
	
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getDoctorNotification() {
		return doctorNotification;
	}
	public void setDoctorNotification(String doctorNotification) {
		this.doctorNotification = doctorNotification;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public Long getAttributeId() {
		return attributeId;
	}
	
	
} 
