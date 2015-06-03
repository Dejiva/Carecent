package com.codecoop.interact.core.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SignsSymptomsLabworkAttrByGroupDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4940870974731420900L;
	private Long signSymtomLabworkId;
	private String signSymtomLabworkName;
	private List<SignsSymptomsLabworkAttrDto> signsSymptomsLabworkAttrlist;


	public SignsSymptomsLabworkAttrByGroupDto() {
	}


	public Long getSignSymtomLabworkId() {
		return signSymtomLabworkId;
	}


	public void setSignSymtomLabworkId(Long signSymtomLabworkId) {
		this.signSymtomLabworkId = signSymtomLabworkId;
	}


	public String getSignSymtomLabworkName() {
		return signSymtomLabworkName;
	}


	public void setSignSymtomLabworkName(String signSymtomLabworkName) {
		this.signSymtomLabworkName = signSymtomLabworkName;
	}


	public List<SignsSymptomsLabworkAttrDto> getSignsSymptomsLabworkAttrlist() {
		return signsSymptomsLabworkAttrlist;
	}


	public void setSignsSymptomsLabworkAttrlist(
			List<SignsSymptomsLabworkAttrDto> signsSymptomsLabworkAttrlist) {
		this.signsSymptomsLabworkAttrlist = signsSymptomsLabworkAttrlist;
	}

	
}
