package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ANNOUNCEMENT", catalog = "INTERACT")
public class Announcement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "ANNOUNCEMENT_TEXT")
	private String announcementText;

	@Column(name = "USER_CREATED")
	private String userCreated;

	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "ACTIVE_FLAG")
	private Integer activeFlag;

	@Column(name = "DELETED_FLAG")
	private Integer deletedFlag;
	
	@Column(name = "FACILITY_ID")
	private Long facilityId;
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnnouncementText() {
		return announcementText;
	}

	public void setAnnouncementText(String announcementText) {
		this.announcementText = announcementText;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Integer deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	
	public void setFacilityId(Long facilityId){
		this.facilityId = facilityId;
		
	}
	public Long getFacilityId() {
		return facilityId;
	}


}
