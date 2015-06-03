package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGE_ELIGIBILITY_STAFF", catalog = "INTERACT")
public class MessageEligibilityStaff implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1030311874761818188L;
	private Long id;
	
	private Long messageAlertScenariosId;
	private Long responsibilitiesId;
	private Boolean suspendedPrevScenarioFlag;
    public	MessageEligibilityStaff(){
		
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
	
	
	
	@Column(name = "MESSAGE_ALERT_SCENARIO_ID")
	public Long getMessageAlertScenariosId() {
		return messageAlertScenariosId;
	}
	public void setMessageAlertScenariosId(Long messageAlertScenariosId) {
		this.messageAlertScenariosId = messageAlertScenariosId;
	}
	@Column(name = "RESPONSIBILITIES_ID")
	public Long getResponsibilitiesId() {
		return responsibilitiesId;
	}
	public void setResponsibilitiesId(Long responsibilitiesId) {
		this.responsibilitiesId = responsibilitiesId;
	}
	@Column(name ="SUSPENDED_PREV_SCENARIO_FLAG")
	public Boolean getSuspendedPrevScenarioFlag() {
		return suspendedPrevScenarioFlag;
	}
	public void setSuspendedPrevScenarioFlag(Boolean suspendedPrevScenarioFlag) {
		this.suspendedPrevScenarioFlag = suspendedPrevScenarioFlag;
	}

	

}
