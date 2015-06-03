package com.codecoop.interact.web.prbean;

import java.io.Serializable;
import java.util.List;

public class CarePathListingPrBean implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1971326733986883595L;
	private String carePathCode;
	private String carePathName;
	private String status;
	private List<String> suggestedBy;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getSuggestedBy() {
		return suggestedBy;
	}
	public void setSuggestedBy(List<String> suggestedBy) {
		this.suggestedBy = suggestedBy;
	}
}
