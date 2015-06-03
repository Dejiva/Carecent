package com.codecoop.interact.core.dto;

import java.util.ArrayList;
import java.util.List;

import com.codecoop.interact.core.domain.Patient;

public class StopAndWatchPatientsDto {

	private Patient patient;
	List<String> repotedBy=new ArrayList<String>();
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public List<String> getRepotedBy() {
		return repotedBy;
	}
	public void setRepotedBy(List<String> repotedBy) {
		this.repotedBy = repotedBy;
	}
 
	
}
