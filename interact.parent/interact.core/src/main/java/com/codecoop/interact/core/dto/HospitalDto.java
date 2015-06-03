package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class HospitalDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7461211397445846619L;
	private Long hospitalId;
	private String hospitalName;
	
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Long getHospitalId() {
		return hospitalId;
	}
	
	
}
