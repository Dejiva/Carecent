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
@Table(name = "PATIENT_CARE_PATHS", catalog = "INTERACT")
public class PatientCarePaths implements java.io.Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5720518268945793015L;
	private Long id;
	private PatientEpisode patientEpisode;
	private CarePath carePath;
	private String patientCarePathXml;

    private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	
	

	public PatientCarePaths() {
	}
	
	
	
	public PatientCarePaths(PatientEpisode patientEpisode, CarePath carePath,
			String patientCarePathXml, Date dateCreated, String userCreated,
			Date dateModified, String userModified) {
		super();
		this.patientEpisode = patientEpisode;
		this.carePath = carePath;
		this.patientCarePathXml = patientCarePathXml;
		this.dateCreated = dateCreated;
		this.userCreated = userCreated;
		this.dateModified = dateModified;
		this.userModified = userModified;
	}



	@Column(name = "CARE_PATH_XML_FILE" ,length = Integer.MAX_VALUE)
	public String getPatientCarePathXml() {
		return patientCarePathXml;
	}
   
	public void setPatientCarePathXml(String patientCarePathXml) {
		this.patientCarePathXml = patientCarePathXml;
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
