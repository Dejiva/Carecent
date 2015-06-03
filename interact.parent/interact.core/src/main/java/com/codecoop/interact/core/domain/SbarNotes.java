package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SBAR_NOTES", catalog = "INTERACT")
public class SbarNotes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6922367192722642509L;
	
	private Long id;
	private Sbar sbar;
	private FacilityStaff nursingFacilityStaff;
	private String nursingStaffType;
	private String notes;
    private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	public SbarNotes() {
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
    
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SBAR_ID")
	public Sbar getSbar() {
		return sbar;
	}

	public void setSbar(Sbar sbar) {
		this.sbar = sbar;
	}
	
	
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NURSING_FACILITY_STAFF_ID")
	public FacilityStaff getNursingFacilityStaff() {
		return nursingFacilityStaff;
	}



	public void setNursingFacilityStaff(FacilityStaff nursingFacilityStaff) {
		this.nursingFacilityStaff = nursingFacilityStaff;
	}



	@Column(name = "NURSING_STAFF_TYPE", length = 50)
	public String getNursingStaffType() {
		return nursingStaffType;
	}

	public void setNursingStaffType(String nursingStaffType) {
		this.nursingStaffType = nursingStaffType;
	}

	@Column(name = "NOTES", length = 5000)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
