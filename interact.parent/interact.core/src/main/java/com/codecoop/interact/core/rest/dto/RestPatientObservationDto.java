package com.codecoop.interact.core.rest.dto;

import java.io.Serializable;
import java.util.List;

public class RestPatientObservationDto implements Serializable {

	private static final long serialVersionUID = 5656941550250015859L;
	
	private List<RestCICDto> cicValues;
	private List<RestLabOrdersDto> labOrderValues;
	private List<RestNotesDto> notesValues;
	
	public List<RestCICDto> getCicValues() {
		return cicValues;
	}
	public void setCicValues(List<RestCICDto> cicValues) {
		this.cicValues = cicValues;
	}
	public List<RestLabOrdersDto> getLabOrderValues() {
		return labOrderValues;
	}
	public void setLabOrderValues(List<RestLabOrdersDto> labOrderValues) {
		this.labOrderValues = labOrderValues;
	}
	public List<RestNotesDto> getNotesValues() {
		return notesValues;
	}
	public void setNotesValues(List<RestNotesDto> notesValues) {
		this.notesValues = notesValues;
	}
	
	
}
