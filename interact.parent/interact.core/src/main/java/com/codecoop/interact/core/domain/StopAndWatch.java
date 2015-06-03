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
@Table(name = "STOP_AND_WATCH", catalog = "INTERACT")
public class StopAndWatch implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1762015211302982995L;
	private Long id;
	private PatientEpisode patientEpisode;
	private Date startDate;
	private Date endDate;
	private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	

	public StopAndWatch() {
	}
	
	public StopAndWatch(PatientEpisode patientEpisode, Date startDate,
			Date endDate, Date dateCreated, String userCreated,
			Date dateModified, String userModified) {
		super();
		this.patientEpisode = patientEpisode;
		this.startDate = startDate;
		this.endDate = endDate;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE", length = 19)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", length = 19)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
