package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "HOSPITAL_TRANSFER_ATTRIBUTES", catalog = "INTERACT", uniqueConstraints = @UniqueConstraint(columnNames = "ATTRIBUTE_CODE"))
public class AcuteCareAttributes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String attributeName;
	private String attributeCode;
	private String valueType;
	private String codeExplained;
	private String formAttrib;

	public AcuteCareAttributes() {
	}

	public AcuteCareAttributes(String attributeName, String attributeCode,
			String valueType, String codeExplained,String formAttrib) {
		this.attributeName = attributeName;
		this.attributeCode = attributeCode;
		this.valueType = valueType;
		this.codeExplained = codeExplained;
		this.formAttrib = formAttrib;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ATTRIBUTE_NAME", length = 100)
	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	@Column(name = "ATTRIBUTE_CODE", unique = true, length = 100)
	public String getAttributeCode() {
		return this.attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	@Column(name = "VALUE_TYPE", length = 100)
	public String getValueType() {
		return this.valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	@Column(name = "CODE_EXPLAINED", length = 300)
	public String getCodeExplained() {
		return this.codeExplained;
	}

	public void setCodeExplained(String codeExplained) {
		this.codeExplained = codeExplained;
	}
	@Column(name = "FORM_ATTRIB", length = 300)
	public String getFormAttrib() {
		return formAttrib;
	}

	public void setFormAttrib(String formAttrib) {
		this.formAttrib = formAttrib;
	}

	@Override
	public String toString() {
		return "AcuteCareAttributes [id=" + id + ", attributeName="
				+ attributeName + ", attributeCode=" + attributeCode
				+ ", valueType=" + valueType + ", codeExplained="
				+ codeExplained + ", formAttrib=" + formAttrib + "]";
	}

}
