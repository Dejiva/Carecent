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
@Table(name = "PATIENT_ENCOUNTER", catalog = "INTERACT")
public class PatientEncounter implements java.io.Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7476683080782376896L;
	private Long id;
	private PatientEpisode patientEpisode;
	
	private Integer encounterNum;
	private Date startDate;
	private Date endDate;
	private String reason;
    private Date dateCreated;
	private String userCreated;
	private Date dateModified;
	private String userModified;
	private Long fromHospitalId;
	private Date fromHsptalAdmissionDate;
	private Date fromHsptalDischargeDate;
	private String formHospitalUnit;
	
	

	public PatientEncounter() {
	}
	
	public PatientEncounter(PatientEpisode patientEpisode,
			Integer encounterNum, Date startDate, Date endDate, String reason,
			Date dateCreated, String userCreated, Date dateModified,
			String userModified,Long fromHospitalId,Date  fromHsptalAdmissionDate,Date fromHsptalDischargeDate,String formHospitalUnit) {
		super();
		this.patientEpisode = patientEpisode;
		this.encounterNum = encounterNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.dateCreated = dateCreated;
		this.userCreated = userCreated;
		this.dateModified = dateModified;
		this.userModified = userModified;
		this.fromHospitalId = fromHospitalId;
		this.fromHsptalAdmissionDate = fromHsptalAdmissionDate;
		this.fromHsptalDischargeDate = fromHsptalDischargeDate;
		this.formHospitalUnit = formHospitalUnit;
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

	@Column(name = "ENCOUNTER_NUM")
	public Integer getEncounterNum() {
		return encounterNum;
	}

    public void setEncounterNum(Integer encounterNum) {
		this.encounterNum = encounterNum;
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
    @Column(name = "REASON", length = 100)
	public String getReason() {
		return reason;
	}

    public void setReason(String reason) {
		this.reason = reason;
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
	
	@Column(name = "FROM_HOSPITAL_ID")
	public Long getFromHospitalId() {
		return fromHospitalId;
	}

	public void setFromHospitalId(Long fromHospitalId) {
		this.fromHospitalId = fromHospitalId;
	}
	@Column(name = "FROM_HSPTAL_ADMISSION_DATE")
	public Date getFromHsptalAdmissionDate() {
		return fromHsptalAdmissionDate;
	}

	public void setFromHsptalAdmissionDate(Date fromHsptalAdmissionDate) {
		this.fromHsptalAdmissionDate = fromHsptalAdmissionDate;
	}
	@Column(name = "FROM_HSPTAL_DISCHARGE_DATE")
	public Date getFromHsptalDischargeDate() {
		return fromHsptalDischargeDate;
	}

	public void setFromHsptalDischargeDate(Date fromHsptalDischargeDate) {
		this.fromHsptalDischargeDate = fromHsptalDischargeDate;
	}
	@Column(name = "FORM_HOSPITAL_UNIT")
	public String getFormHospitalUnit() {
		return formHospitalUnit;
	}

	public void setFormHospitalUnit(String formHospitalUnit) {
		this.formHospitalUnit = formHospitalUnit;
	}

}
