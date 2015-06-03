package com.codecoop.interact.web.model;

import java.io.Serializable;
import java.util.List;

import com.codecoop.interact.core.dto.CarePathLabworksDto;

public class CarePathLabworksResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<CarePathLabworksDto> labworks;
	private String status;
	
	public List<CarePathLabworksDto> getLabworks() {
		return labworks;
	}
	public void setLabworks(List<CarePathLabworksDto> labworks) {
		this.labworks = labworks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
