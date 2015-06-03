package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "CARE_PATH", catalog = "INTERACT")
public class CarePath implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2702335320296852295L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "CARE_PATH_CODE", unique = true,length = 20)
	private String carePathCode;
	
	@Column(name = "CARE_PATH_NAME",length = 100)
	private String carePathName;
	
	@Column(name = "XML_FILE_LOCATION", length = 250)
	private String carePathLocation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED", length = 19)
	private Date dateCreated;
	
	@Column(name = "USER_CREATED", length = 50)
	private String userCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_MODIFIED", length = 19)
	private Date dateModified;
	
	@Column(name = "USER_MODIFIEd", length = 50)
	private String userModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarePathCode() {
		return carePathCode;
	}

	public void setCarePathCode(String carePathCode) {
		this.carePathCode = carePathCode;
	}

	public String getCarePathName() {
		return carePathName;
	}

	public void setCarePathName(String carePathName) {
		this.carePathName = carePathName;
	}

	public String getCarePathLocation() {
		return carePathLocation;
	}

	public void setCarePathLocation(String carePathLocation) {
		this.carePathLocation = carePathLocation;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getUserModified() {
		return userModified;
	}

	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}


	
	
}
