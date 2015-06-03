package com.codecoop.interact.core.domain;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "MESSAGE_ALERT", catalog = "INTERACT")
public class MessageAlert implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6270403868762917734L;
	/**
	 * 
	 */
   
	private Long id;
	private String msgSubj;
	private String msgBody;
	private String messageAlertType;
	private Long eventId;
	private String msgScenarioCode;
	private Long patientEpisodeId;
	private Long assignedFacilityStaffId;
    private Set<MessageAlertDetails> messageAlertDetailsList=new HashSet<MessageAlertDetails>();
    public MessageAlert()
    {
    	
    }
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "messageAlert")
	public Set<MessageAlertDetails> getmessageAlertDetailsList() {
		return messageAlertDetailsList;
	}

	public void setmessageAlertDetailsList(Set<MessageAlertDetails> messageAlertDetailsList) {
		this.messageAlertDetailsList = messageAlertDetailsList;
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

	@Column(name = "MSG_SUBJ", length = 50)
	public String getMsgSubj() {
		return msgSubj;
	}

	public void setMsgSubj(String msgSubj) {
		this.msgSubj = msgSubj;
	}
	@Column(name = "MSG_BODY", length = 100)
	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	@Column(name = "MSG_ALERT_TYPE", length = 38)
	public String getMessageAlertType() {
		return messageAlertType;
	}

	public void setMessageAlertType(String messageAlertType) {
		this.messageAlertType = messageAlertType;
	}
	@Column(name = "EVENT_ID")
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	@Column(name = "MSG_SCENARIO_CODE", length = 38)
	public String getMsgScenarioCode() {
		return msgScenarioCode;
	}
	public void setMsgScenarioCode(String msgScenarioCode) {
		this.msgScenarioCode = msgScenarioCode;
	}
	
	@Column(name ="PATIENT_EPISODE_ID")
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}
	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}
	
	@Column(name ="ASSIGN_FACILITY_STAFF_ID")
	public Long getAssignedFacilityStaffId() {
		return assignedFacilityStaffId;
	}
	public void setAssignedFacilityStaffId(Long assignedFacilityStaffId) {
		this.assignedFacilityStaffId = assignedFacilityStaffId;
	}

	
	
	


}
