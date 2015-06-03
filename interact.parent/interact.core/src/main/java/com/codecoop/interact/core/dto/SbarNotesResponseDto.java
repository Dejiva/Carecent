package com.codecoop.interact.core.dto;


import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class SbarNotesResponseDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4944585684601184770L;
	
	private String staffName;
	private Long staffId;
	private List<NurseNotesDto>  nurseNotesList;
	
	
	

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public List<NurseNotesDto> getNurseNotesList() {
		return nurseNotesList;
	}

	public void setNurseNotesList(List<NurseNotesDto> nurseNotesList) {
		this.nurseNotesList = nurseNotesList;
	}

	
	
}
