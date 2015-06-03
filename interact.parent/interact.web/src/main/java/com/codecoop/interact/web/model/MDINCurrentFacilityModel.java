package com.codecoop.interact.web.model;

import java.io.Serializable;

public class MDINCurrentFacilityModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String workNumber;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorkNumber() {
		return workNumber;
	}
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}
	
	

}
