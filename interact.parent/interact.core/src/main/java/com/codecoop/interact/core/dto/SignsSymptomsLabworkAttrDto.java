package com.codecoop.interact.core.dto;

import org.springframework.stereotype.Component;

@Component
public class SignsSymptomsLabworkAttrDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4940870974731420900L;
	private Long id;
	private Long parentId;
	private String attrName;
	private String attrValue;
	private Boolean immediateFlag;
	private String datatype;
	private String units;
	private String carePathAttrCode;

	public SignsSymptomsLabworkAttrDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Boolean getImmediateFlag() {
		return immediateFlag;
	}
	
	public void setImmediateFlag(Boolean immediateFlag) {
		this.immediateFlag = immediateFlag;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getCarePathAttrCode() {
		return carePathAttrCode;
	}

	public void setCarePathAttrCode(String carePathAttrCode) {
		this.carePathAttrCode = carePathAttrCode;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentId() {
		return parentId;
	}
}
