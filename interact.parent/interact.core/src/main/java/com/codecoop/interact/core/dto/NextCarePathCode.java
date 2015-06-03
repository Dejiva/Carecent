package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class NextCarePathCode implements Serializable{

	private static final long serialVersionUID = -340849948964355705L;
	
	private String carePathCode;
	private boolean scanned;
	public String getCarePathCode() {
		return carePathCode;
	}
	public void setCarePathCode(String carePathCode) {
		this.carePathCode = carePathCode;
	}
	public boolean isScanned() {
		return scanned;
	}
	public void setScanned(boolean scanned) {
		this.scanned = scanned;
	}
}
