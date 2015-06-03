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
 * MedicineResolution generated by hbm2java
 */
@Entity
@Table(name = "MEDICINE_RESOLUTION", catalog = "INTERACT")
public class MedicineResolution implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2442251553994000847L;
	private Long id;
	private String resolutionType;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;

	public MedicineResolution() {
	}

	public MedicineResolution(String resolutionType, Date dateCreated,
			String userCreated, Date dateModified, String userModified) {
		this.resolutionType = resolutionType;
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

	@Column(name = "RESOLUTION_TYPE", length = 50)
	public String getResolutionType() {
		return this.resolutionType;
	}

	public void setResolutionType(String resolutionType) {
		this.resolutionType = resolutionType;
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