package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class CarePathLabworksValuesFrmCICDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long cicId;
	private Long sbarId;
	private Long sslattrId;
	private String sslattrName;
	private String cicValue;

	public Long getCicId() {
		return cicId;
	}
	public void setCicId(Long cicId) {
		this.cicId = cicId;
	}

	public Long getSslattrId() {
		return sslattrId;
	}
	public void setSslattrId(Long sslattrId) {
		this.sslattrId = sslattrId;
	}
	public String getSslattrName() {
		return sslattrName;
	}
	public void setSslattrName(String sslattrName) {
		this.sslattrName = sslattrName;
	}
	public String getCicValue() {
		return cicValue;
	}
	public void setCicValue(String cicValue) {
		this.cicValue = cicValue;
	}
	public Long getSbarId() {
		return sbarId;
	}
	public void setSbarId(Long sbarId) {
		this.sbarId = sbarId;
	}

}
