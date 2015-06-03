package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.List;

public class ViewAllCarePathsResponseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2567570491005219401L;
	
	
	private String carePathName;
	private Long carePathId;
	private List<CarePathStepsResponseDto> carePathSteps;
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
	public List<CarePathStepsResponseDto> getCarePathSteps() {
		return carePathSteps;
	}
	public void setCarePathSteps(List<CarePathStepsResponseDto> carePathSteps) {
		this.carePathSteps = carePathSteps;
	}
	

	
}
