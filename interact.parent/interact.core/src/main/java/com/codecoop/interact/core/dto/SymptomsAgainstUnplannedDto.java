package com.codecoop.interact.core.dto;

public class SymptomsAgainstUnplannedDto {

	private String symtomName;
	private Long   numberOfTransfers;
	private Float  transferPercent;
	public String getSymtomName() {
		return symtomName;
	}
	public void setSymtomName(String symtomName) {
		this.symtomName = symtomName;
	}
	
	public Long getNumberOfTransfers() {
		return numberOfTransfers;
	}
	public void setNumberOfTransfers(Long numberOfTransfers) {
		this.numberOfTransfers = numberOfTransfers;
	}
	public Float getTransferPercent() {
		return transferPercent;
	}
	public void setTransferPercent(Float transferPercent) {
		this.transferPercent = transferPercent;
	}
	


}
