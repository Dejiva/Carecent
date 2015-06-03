package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class AdmissionAttributesDto implements Serializable {
	private static final long serialVersionUID = 3543171958040972540L;
	private Long id;
	private String attributeName;
	private String attributeCode;
	private String valueType;
	private String codeExplained;
	private String formAttribute;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeCode() {
		return attributeCode;
	}
	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public String getCodeExplained() {
		return codeExplained;
	}
	public void setCodeExplained(String codeExplained) {
		this.codeExplained = codeExplained;
	}
	public String getFormAttribute() {
		return formAttribute;
	}
	public void setFormAttribute(String formAttribute) {
		this.formAttribute = formAttribute;
	}
	
}
