package com.codecoop.interact.core.dto;

import java.util.ArrayList;
import java.util.List;

public class SystemNotesDto {

	private String  stepType;
	private List<String>  instructionList;
	public String getStepType() {
		return stepType;
	}
	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	public List<String> getInstuctionsList() {
		if(instructionList.isEmpty()){
			instructionList= new ArrayList<String>();
		}
		return instructionList;
	}
	public void setInstuctionsList(List<String> instructionList) {
		this.instructionList = instructionList;
	}
	
	
}
