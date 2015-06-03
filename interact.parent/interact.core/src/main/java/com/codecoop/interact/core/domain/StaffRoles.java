package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "STAFF_ROLES", catalog = "INTERACT")
public class StaffRoles implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5873959491059142192L;
	private Long id;
	private String roleName;
	private String description;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	private Long  responsibilitiesId;
	private Set<FacilityStaff> facilityStaffs = new HashSet<FacilityStaff>(0);

	public StaffRoles() {
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

	@Column(name = "ROLE_NAME", length = 20)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "staffRoles")
	public Set<FacilityStaff> getFacilityStaffs() {
		return this.facilityStaffs;
	}

	public void setFacilityStaffs(Set<FacilityStaff> facilityStaffs) {
		this.facilityStaffs = facilityStaffs;
	}
	@Column(name = "RESPONSIBILITIES_ID")
	public Long getResponsibilitiesId() {
		return responsibilitiesId;
	}

	public void setResponsibilitiesId(Long responsibilitiesId) {
		this.responsibilitiesId = responsibilitiesId;
	}

	
	
	
	

}
