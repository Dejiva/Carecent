package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PATIENT_CARE_PATH_TRACKER", catalog = "INTERACT")
public class PatientCarePathTracker implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8472538733045522920L;
	private Long id;
	private PatientEpisode patientEpisode;
	private CarePath carePath;
	private String carePathAttrCode;
	private String carePathAttrName;
	private Integer stepId;
	private String value;
	private Boolean notifyDoctorFlag;
    private Integer exceSequence;
    private String minValue;
    private String maxValue;
    private String dataType;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	

	public PatientCarePathTracker() {
	}
	
	public PatientCarePathTracker(PatientEpisode patientEpisode,
			CarePath carePath, String carePathAttrCode,
			String carePathAttrName, Integer stepId, String value,
			Boolean notifyDoctorFlag, Date dateCreated, String userCreated,
			Date dateModified, String userModified) {
		super();
		this.patientEpisode = patientEpisode;
		this.carePath = carePath;
		this.carePathAttrCode = carePathAttrCode;
		this.carePathAttrName = carePathAttrName;
		this.stepId = stepId;
		this.value = value;
		this.notifyDoctorFlag = notifyDoctorFlag;
		this.dateCreated = dateCreated;
		this.userCreated = userCreated;
		this.dateModified = dateModified;
		this.userModified = userModified;
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
    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PATIENT_EPISODE_ID")
	public PatientEpisode getPatientEpisode() {
		return patientEpisode;
	}

	public void setPatientEpisode(PatientEpisode patientEpisode) {
		this.patientEpisode = patientEpisode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CARE_PATH_ID")
	public CarePath getCarePath() {
		return carePath;
	}

	public void setCarePath(CarePath carePath) {
		this.carePath = carePath;
	}
	@Column(name = "EXCE_SEQUENCE")
	public Integer getExceSequence() {
		return exceSequence;
	}

	public void setExceSequence(Integer exceSequence) {
		this.exceSequence = exceSequence;
	}
	@Column(name = "STEP_ID")
	public Integer getStepId() {
		return stepId;
	}
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	@Column(name = "CARE_PATH_ATTR_CODE", length = 50)
	public String getCarePathAttrCode() {
		return carePathAttrCode;
	}

	public void setCarePathAttrCode(String carePathAttrCode) {
		this.carePathAttrCode = carePathAttrCode;
	}
	@Column(name = "CARE_PATH_ATTR_NAME", length = 100)
	public String getCarePathAttrName() {
		return carePathAttrName;
	}

	public void setCarePathAttrName(String carePathAttrName) {
		this.carePathAttrName = carePathAttrName;
	}

	@Column(name = "VALUE", length = 50)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@Column(name = "NOTIFY_DOCTOR_FLAG")
	public Boolean getNotifyDoctorFlag() {
		return notifyDoctorFlag;
	}

	public void setNotifyDoctorFlag(Boolean notifyDoctorFlag) {
		this.notifyDoctorFlag = notifyDoctorFlag;
	}
	@Column(name = "MIN_VALUE", length = 45)
	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	@Column(name = "MAX_VALUE", length = 45)
	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	@Column(name = "DATA_TYPE", length = 45)
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED", length = 19)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "USER_CREATED", length = 50)
	public String getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFIED", length = 19)
	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Column(name = "USER_MODIFIED", length = 50)
	public String getUserModified() {
		return this.userModified;
	}

	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}


}
