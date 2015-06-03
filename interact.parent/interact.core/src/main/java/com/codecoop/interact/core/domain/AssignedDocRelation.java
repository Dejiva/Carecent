package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="ASSIGNED_DOC_RELATION", catalog = "INTERACT")
public class AssignedDocRelation implements Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = 8008525591248910562L;
private Long id;
private Long facilityStaffId;
private Long docFacilityStaffId;
public AssignedDocRelation()
{
	
}
@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "ID", unique = true, nullable = false)
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
@Column(name ="FACILITY_STAFF_ID")
public Long getFacilityStaffId() {
	return facilityStaffId;
}
public void setFacilityStaffId(Long facilityStaffId) {
	this.facilityStaffId = facilityStaffId;
}
@Column(name ="DOCTOR_FACILITY_STAFF_ID")
public Long getDocFacilityStaffId() {
	return docFacilityStaffId;
}
public void setDocFacilityStaffId(Long docFacilityStaffId) {
	this.docFacilityStaffId = docFacilityStaffId;
}



}
