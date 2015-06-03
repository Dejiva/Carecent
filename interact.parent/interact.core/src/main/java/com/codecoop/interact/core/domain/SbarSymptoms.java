package com.codecoop.interact.core.domain;

// Generated Aug 30, 2014 3:46:09 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MedicalReconciliation generated by hbm2java
 */
@Entity
@Table(name = "SBAR_SYMPTOMS", catalog = "INTERACT")
public class SbarSymptoms implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763878407447632206L;
	private Long id;
	private Long sbarId;
	private String code;
	private Boolean boolValue;
	private String textValue;
	private Date dateValue;
	
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;

	public SbarSymptoms() {
	}

	

	public SbarSymptoms(Long sbarId, String code,
			Boolean boolValue, String textValue, Date dateValue,
			Date dateCreated, String userCreated, Date dateModified,
			String userModified) {
		super();
		this.sbarId = sbarId;
		this.code = code;
		this.boolValue = boolValue;
		this.textValue = textValue;
		this.dateValue = dateValue;
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

	@Column(name = "SBAR_ID")
	public Long getSbarId() {
		return sbarId;
	}

	public void setSbarId(Long sbarId) {
		this.sbarId = sbarId;
	}

	@Column(name = "BOOL_VALUE")
	public Boolean getBoolValue() {
		return boolValue;
	}


	public void setBoolValue(Boolean boolValue) {
		this.boolValue = boolValue;
	}


	@Column(name = "TEXT_VALUE", length = 200)
	public String getTextValue() {
		return textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_VALUE", length = 19)
	
	public Date getDateValue() {
		return dateValue;
	}



	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}



	@Column(name = "CODE", length = 100)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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