package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIGNS_SYMPTOMS_LABWORK", catalog = "INTERACT")
public class SignsSymptomsLabwork implements java.io.Serializable {

	
	private static final long serialVersionUID = 4082934018083099143L;
	private Long id;
	private String signsSymptomsLabworkName;
	private Boolean signFlag;
	private Boolean symptomFlag;
	private Boolean labworkFlag;
	private String carepathLabworkCode;

	public SignsSymptomsLabwork() {
	}

	public SignsSymptomsLabwork(String signsSymptomsLabworkName,
			Boolean signFlag, Boolean symptomFlag, Boolean labworkFlag) {
		this.signsSymptomsLabworkName = signsSymptomsLabworkName;
		this.signFlag = signFlag;
		this.symptomFlag = symptomFlag;
		this.labworkFlag = labworkFlag;
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

	@Column(name = "SIGNS_SYMPTOMS_LABWORK_NAME", length = 60)
	public String getSignsSymptomsLabworkName() {
		return this.signsSymptomsLabworkName;
	}

	public void setSignsSymptomsLabworkName(String signsSymptomsLabworkName) {
		this.signsSymptomsLabworkName = signsSymptomsLabworkName;
	}

	@Column(name = "SIGN_FLAG")
	public Boolean getSignFlag() {
		return this.signFlag;
	}

	public void setSignFlag(Boolean signFlag) {
		this.signFlag = signFlag;
	}

	@Column(name = "SYMPTOM_FLAG")
	public Boolean getSymptomFlag() {
		return this.symptomFlag;
	}

	public void setSymptomFlag(Boolean symptomFlag) {
		this.symptomFlag = symptomFlag;
	}

	@Column(name = "LABWORK_FLAG")
	public Boolean getLabworkFlag() {
		return this.labworkFlag;
	}

	public void setLabworkFlag(Boolean labworkFlag) {
		this.labworkFlag = labworkFlag;
	}
	@Column(name = "CARE_PATH_LABWORK_CODE")
	public String getCarepathLabworkCode() {
		return carepathLabworkCode;
	}

	public void setCarepathLabworkCode(String carepathLabworkCode) {
		this.carepathLabworkCode = carepathLabworkCode;
	}

}
