package com.codecoop.interact.core.dto;

import java.util.List;

public class EvaluationMessageDto {

	private Long patientId;
	
	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	private List<CarePathNotifyDoctorMessageDto>  carePathNotifyDoctorMessageList;
	private List<ChangeInConditionMessageDto>  changeInConditionMessageList;
	private List<CarePathSuggestionMessageDto> carePathSuggestionMessageList;

	public List<CarePathSuggestionMessageDto> getCarePathSuggestionMessageList() {
		return carePathSuggestionMessageList;
	}

	public void setCarePathSuggestionMessageList(
			List<CarePathSuggestionMessageDto> carePathSuggestionMessageList) {
		this.carePathSuggestionMessageList = carePathSuggestionMessageList;
	}

	public List<CarePathNotifyDoctorMessageDto> getCarePathNotifyDoctorMessageList() {
		return carePathNotifyDoctorMessageList;
	}

	public void setCarePathNotifyDoctorMessageList(
			List<CarePathNotifyDoctorMessageDto> carePathNotifyDoctorMessageList) {
		this.carePathNotifyDoctorMessageList = carePathNotifyDoctorMessageList;
	}

	public List<ChangeInConditionMessageDto> getChangeInConditionMessageList() {
		return changeInConditionMessageList;
	}

	public void setChangeInConditionMessageList(
			List<ChangeInConditionMessageDto> changeInConditionMessageList) {
		this.changeInConditionMessageList = changeInConditionMessageList;
	}

	
	
	
}
