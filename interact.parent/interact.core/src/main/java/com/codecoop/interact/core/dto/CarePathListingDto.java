package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CarePathListingDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2567570491005219401L;
	
	private String carePathCode;
	private String carePathName;
	private Long carePathId;
	private String status;
	private Set<String> suggestions;
	
	public String getCarePathCode() {
		return carePathCode;
	}
	public void setCarePathCode(String carePathCode) {
		this.carePathCode = carePathCode;
	}
	public String getCarePathName() {
		return carePathName;
	}
	public void setCarePathName(String carePathName) {
		this.carePathName = carePathName;
	}
	public Long getCarePathId() {
		return carePathId;
	}
	public void setCarePathId(Long carePathId) {
		this.carePathId = carePathId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<String> getSuggestions() {
		if(this.suggestions == null){
			this.suggestions = new HashSet<String>(); 
		}
		return suggestions;
	}
	public void setSuggestions(HashSet<String> suggestions) {
		this.suggestions = suggestions;
	}
	
	@Override
	public boolean equals(Object carePath) {
		return this.carePathCode.equals(((CarePathListingDto)carePath).getCarePathCode());
	}
	@Override
	public String toString() {
		return "CarePathListingDto [carePathCode=" + carePathCode
				+ ", carePathName=" + carePathName + ", carePathId="
				+ carePathId + ", status=" + status + ", suggestions="
				+ suggestions + "]";
	}
	
}
