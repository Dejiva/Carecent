package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class AcuteCareAttributesValuesDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pEpisodeId;
	private Long acuteCareAttributeId;
	private String attributeValue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getpEpisodeId() {
		return pEpisodeId;
	}
	public void setpEpisodeId(Long pEpisodeId) {
		this.pEpisodeId = pEpisodeId;
	}
	public Long getAcuteCareAttributeId() {
		return acuteCareAttributeId;
	}
	public void setAcuteCareAttributeId(Long acuteCareAttributeId) {
		this.acuteCareAttributeId = acuteCareAttributeId;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	@Override
	public String toString() {
		return "AcuteCareAttributesValuesDto [id=" + id + ", pEpisodeId="
				+ pEpisodeId + ", acuteCareAttributeId=" + acuteCareAttributeId
				+ ", attributeValue=" + attributeValue + "]";
	}
}
