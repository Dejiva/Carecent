package com.codecoop.interact.web.model;

import java.util.List;

import com.codecoop.interact.core.dto.SignsSymptomsLabworkDto;

/**
 * @author Ramesh
 *
 */
public class LabworksResponse {
	
	private List<SignsSymptomsLabworkDto> list;
	private String status;
	
	public List<SignsSymptomsLabworkDto> getList() {
		return list;
	}
	public void setList(List<SignsSymptomsLabworkDto> list) {
		this.list = list;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
