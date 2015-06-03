package com.codecoop.interact.core.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SymptomsAgainstUnplannedResponseDto {

	
	private Long   totalTransfers;
    private List<SymptomsAgainstUnplannedDto> symptomsAgainstUnplanned;
    private String startDate;
    private String endDate;
    
	public Long getTotalTransfers() {
		return totalTransfers;
	}
	public void setTotalTransfers(Long totalTransfers) {
		this.totalTransfers = totalTransfers;
	}
	public List<SymptomsAgainstUnplannedDto> getSymptomsAgainstUnplanned() {
		return symptomsAgainstUnplanned;
	}
	public void setSymptomsAgainstUnplanned(
			List<SymptomsAgainstUnplannedDto> symptomsAgainstUnplanned) {
		this.symptomsAgainstUnplanned = symptomsAgainstUnplanned;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
