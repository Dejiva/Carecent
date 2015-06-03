package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="PRESCRIBE_MEDICINE", catalog = "INTERACT")
public class PrescribeMedicine implements Serializable{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private Long Id;
private Long patientEpisodeId;
private Long facilityStaffId;
private String medicineName;
private boolean deleteFlag;
@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "ID", unique = true, nullable = false)
public Long getId() {
	return Id;
}
public void setId(Long id) {
	Id = id;
}
@Column(name ="MEDICINE_NAME")
public String getMedicineName() {
	return medicineName;
}
public void setMedicineName(String medicineName) {
	this.medicineName = medicineName;
}
@Column(name ="PATIENT_EPISODE_ID",length=25)
public Long getPatientEpisodeId() {
	return patientEpisodeId;
}
public void setPatientEpisodeId(Long patientEpisodeId) {
	this.patientEpisodeId = patientEpisodeId;
}
@Column(name ="DOCTER_FACILITY_STAFF_ID",length=25)
public Long getFacilityStaffId() {
	return facilityStaffId;
}
public void setFacilityStaffId(Long facilityStaffId) {
	this.facilityStaffId = facilityStaffId;
}
@Column(name="Delete_FLAG")
public boolean isDeleteFlag() {
	return deleteFlag;
}
public void setDeleteFlag(boolean deleteFlag) {
	this.deleteFlag = deleteFlag;
}


}
