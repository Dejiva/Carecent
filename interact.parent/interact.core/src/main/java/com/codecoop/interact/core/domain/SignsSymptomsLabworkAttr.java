package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIGNS_SYMPTOMS_LABWORK_ATTR", catalog = "INTERACT")
public class SignsSymptomsLabworkAttr implements java.io.Serializable {

	private static final long serialVersionUID = -5361931755315491319L;
	private Long id;
	private Long parentId;
	private String attrName;
	private String datatype;
//	private Boolean booleanValue;
	private String units;
	private String carePathAttrCode;

	public SignsSymptomsLabworkAttr() {
	}


	public SignsSymptomsLabworkAttr(Long id, Long parentId, String attrName,
			String datatype, String units, String carePathAttrCode) {
		this.id = id;
		this.parentId = parentId;
		this.attrName = attrName;
		this.datatype = datatype;
		this.units = units;
		this.carePathAttrCode = carePathAttrCode;
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

	@Column(name = "PARENT_ID")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "ATTR_NAME", length = 60)
	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}


	@Column(name = "DATATYPE", length = 50)
	public String getDatatype() {
		return this.datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

/*	@Column(name = "BOOLEAN_VALUE")
	public Boolean getBooleanValue() {
		return this.booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}*/

	@Column(name = "UNITS", length = 60)
	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	@Column(name = "CARE_PATH_ATTR_CODE", length = 50)
	public String getCarePathAttrCode() {
		return carePathAttrCode;
	}

	public void setCarePathAttrCode(String carePathAttrCode) {
		this.carePathAttrCode = carePathAttrCode;
	}

	
	
}
