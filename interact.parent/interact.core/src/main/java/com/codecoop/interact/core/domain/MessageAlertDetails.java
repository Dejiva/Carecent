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
@Table(name ="MESSAGE_ALERT_DETAILS", catalog = "INTERACT")
public class MessageAlertDetails implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8970365569306169183L;
	private Long id;
	private Long facilityStaffId;
	private Boolean readFlag;
	private Boolean suspendedFlag;
	private MessageAlert messageAlert;
	private Date sentDate;
	private Boolean deleteFlag;
	private Boolean assignedFlag;
	public MessageAlertDetails() {
	}
	
	

	
	@Column(name ="TO_FACILITY_STAFF_ID")
	public Long getFacilityStaffId() {
		return facilityStaffId;
	}

	public void setFacilityStaffId(Long facilityStaffId) {
		this.facilityStaffId = facilityStaffId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="SENT_DATE", length = 19)
	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	@Column(name = "READ_FLAG")
	public Boolean getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Boolean readFlag) {
		this.readFlag = readFlag;
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
	@JoinColumn(name ="MESSAGE_ALERT_ID")
	public MessageAlert getMessageAlert() {
		return messageAlert;
	}

	public void setMessageAlert(MessageAlert messageAlert) {
		this.messageAlert = messageAlert;
	}


	@Column(name ="DELETE_FLAG")
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	
	@Column(name ="SUSPENDED_FLAG")
	public Boolean getSuspendedFlag() {
		return suspendedFlag;
	}

	public void setSuspendedFlag(Boolean suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	@Column(name ="ASSIGNED_FLAG")
	public Boolean getAssignedFlag() {
		return assignedFlag;
	}

	public void setAssignedFlag(Boolean assignedFlag) {
		this.assignedFlag = assignedFlag;
	}

	
}
