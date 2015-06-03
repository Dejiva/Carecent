package com.codecoop.interact.web.rest.bean;

import java.util.List;

import com.codecoop.interact.core.rest.dto.ShortNotesDto;



public class ShortNotesResponse {
	private String status;
	private List<ShortNotesDto> notes;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ShortNotesDto> getNotes() {
		return notes;
	}
	public void setNotes(List<ShortNotesDto> notes) {
		this.notes = notes;
	}
	

}
