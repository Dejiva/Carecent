package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PatientSbarDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3040784548021565334L;
	private Long patientEpisodeId;
	private List<ChangeInConditionDto> symptomsConditionsList = new ArrayList<ChangeInConditionDto>();
	
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	public List<ChangeInConditionDto> getSymptomsConditionsList() {
		return symptomsConditionsList;
	}
	public void setSymptomsConditionsList(
			List<ChangeInConditionDto> symptomsConditionsList) {
		this.symptomsConditionsList = symptomsConditionsList;
	}
}
