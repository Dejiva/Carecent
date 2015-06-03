package com.codecoop.interact.web.model;

import java.util.List;

import com.codecoop.interact.core.dto.CarePathLabworksValuesFrmCICDto;

public class CarePathLabworksValuesFrmCICResponse {
	
	private List<CarePathLabworksValuesFrmCICDto> list;
	private String status;
	
	public List<CarePathLabworksValuesFrmCICDto> getList() {
		return list;
	}
	public void setList(List<CarePathLabworksValuesFrmCICDto> list) {
		this.list = list;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
