package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CIC_DECISION_PARMS", catalog = "INTERACT")
public class CicDecisionParms implements java.io.Serializable {

	private static final long serialVersionUID = -5361931755315491319L;
	private Long id;
	private Long SignsSymptomsLabworkAttrId;
	private Boolean immediateFlag;
	private String minValue;
	private String maxValue;

	public CicDecisionParms() {
	}

	public CicDecisionParms(Long id,
			Long signsSymptomsLabworkAttrId, Boolean immediateFlag,
			String minValue, String maxValue) {
		super();
		this.id = id;
		SignsSymptomsLabworkAttrId = signsSymptomsLabworkAttrId;
		this.immediateFlag = immediateFlag;
		this.minValue = minValue;
		this.maxValue = maxValue;
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
	
	@Column(name = "SIGNS_SYMPTOMS_LABWORK_ATTR_ID")
	public Long getSignsSymptomsLabworkAttrId() {
		return SignsSymptomsLabworkAttrId;
	}

	public void setSignsSymptomsLabworkAttrId(Long signsSymptomsLabworkAttrId) {
		SignsSymptomsLabworkAttrId = signsSymptomsLabworkAttrId;
	}

	@Column(name = "IMMEDIATE_FLAG")
	public Boolean getImmediateFlag() {
		return this.immediateFlag;
	}

	public void setImmediateFlag(Boolean immediateFlag) {
		this.immediateFlag = immediateFlag;
	}

	@Column(name = "MIN_VALUE", length = 10)
	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	
	@Column(name = "MAX_VALUE",length = 10)
	public String getMaxValue() {
		return this.maxValue;
	}
	
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	
}
