package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class AdmissionAttributeValuesDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pEpisodeId;
	private Long admissionAttributeId;
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
	public Long getAdmissionAttributeId() {
		return admissionAttributeId;
	}
	public void setAdmissionAttributeId(Long admissionAttributeId) {
		this.admissionAttributeId = admissionAttributeId;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	@Override
	public String toString() {
		return "AdmissionAttributeValuesDto [id=" + id + ", pEpisodeId="
				+ pEpisodeId + ", admissionAttributeId=" + admissionAttributeId
				+ ", attributeValue=" + attributeValue + "]";
	}

}
