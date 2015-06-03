package com.codecoop.interact.core.dto;

import java.io.Serializable;
import java.util.Map;

public class AdmissionAttributesValuesDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5848096619763484662L;
	
	private Long pEpisodeId;
	private Map<String, String> attributeValuesMap;
	
	public void setpEpisodeId(Long pEpisodeId) {
		this.pEpisodeId = pEpisodeId;
	}
	public Long getpEpisodeId() {
		return pEpisodeId;
	}
	public void setAttributeValuesMap(Map<String, String> attributeValuesMap) {
		this.attributeValuesMap = attributeValuesMap;
	}
	public Map<String, String> getAttributeValuesMap() {
		return attributeValuesMap;
	}
	
	
}
