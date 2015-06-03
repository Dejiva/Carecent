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
@Table(name = "CARE_PATH_TRACKER_ATTR", catalog = "INTERACT")
public class CarePathTrackerAttr implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8472538733045522920L;
	private Long id;
	private CarePathTracker carePathTracker;
	private String carePathAttrCode;
	private String carePathAttrName;
	private String value;
	private Boolean notifyDoctorFlag;
    private String minValue;
    private String maxValue;
    private String dataType;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	

	public CarePathTrackerAttr() {
	}
	
	public CarePathTrackerAttr(	CarePathTracker carePathTracker, String carePathAttrCode,
			String carePathAttrName,  String value,
			Boolean notifyDoctorFlag, Date dateCreated, String userCreated,
			Date dateModified, String userModified) {
		super();
		this.carePathTracker = carePathTracker;
		this.carePathAttrCode = carePathAttrCode;
		this.carePathAttrName = carePathAttrName;
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
	@JoinColumn(name = "CARE_PATH_TRACKER_ID")
	public CarePathTracker getCarePathTracker() {
		return carePathTracker;
	}

	public void setCarePathTracker(CarePathTracker carePathTracker) {
		this.carePathTracker = carePathTracker;
	}

	@Column(name = "CARE_PATH_ATTR_CODE", length = 50)
	public String getCarePathAttrCode() {
		return carePathAttrCode;
	}

	public void setCarePathAttrCode(String carePathAttrCode) {
		this.carePathAttrCode = carePathAttrCode;
	}
	@Column(name = "CARE_PATH_ATTR_NAME", length = 200)
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
	@Column(name = "MIN_VALUE", length = 10)
	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	@Column(name = "MAX_VALUE", length = 10)
	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	@Column(name = "DATA_TYPE", length = 10)
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
