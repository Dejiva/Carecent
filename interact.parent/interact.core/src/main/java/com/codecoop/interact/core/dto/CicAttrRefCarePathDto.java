package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class CicAttrRefCarePathDto implements Serializable{

	private static final long serialVersionUID = 6262816716875066843L;

	private String refCarePathCode;
	private String attrName;
	public String getRefCarePathCode() {
		return refCarePathCode;
	}
	public void setRefCarePathCode(String refCarePathCode) {
		this.refCarePathCode = refCarePathCode;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
}
