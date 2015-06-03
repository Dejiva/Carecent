package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class CarePathSuggestionMessageDto implements Serializable{

	private static final long serialVersionUID = -340849948964355705L;
	
	private String carePathName;
	private String sugestion;
	public String getCarePathName() {
		return carePathName;
	}
	public void setCarePathName(String carePathName) {
		this.carePathName = carePathName;
	}
	public String getSugestion() {
		return sugestion;
	}
	public void setSugestion(String sugestion) {
		this.sugestion = sugestion;
	}
	
	
	
	
}
