package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALERTS_TO_ASSIGNED", catalog = "INTERACT")
public class AlertsToAssigned implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3797155410153999597L;
	private Long id;
	private Boolean sendToAssign;
	private String alertTemplateAssign;
	private Long messageAlertScenarioId;
	private Boolean suspendedPrevScenarioFlag;
	public AlertsToAssigned()
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
	@Column(name = "SEND_TO_ASSIGN")
	public Boolean getSendToAssign() {
		return sendToAssign;
	}
	public void setSendToAssign(Boolean sendToAssign) {
		this.sendToAssign = sendToAssign;
	}
	@Column(name = "ALERT_TEMPLATE_ASSIGNED",length=500)
	public String getAlertTemplateAssign() {
		return alertTemplateAssign;
	}
	public void setAlertTemplateAssign(String alertTemplateAssign) {
		this.alertTemplateAssign = alertTemplateAssign;
	}
	
	@Column(name = "MESSAGE_ALERT_SCENARIO_ID")
	public Long getMessageAlertScenarioId() {
		return messageAlertScenarioId;
	}
	public void setMessageAlertScenarioId(Long messageAlertScenarioId) {
		this.messageAlertScenarioId = messageAlertScenarioId;
	}
	
	@Column(name ="SUSPENDED_PREV_SCENARIO_FLAG")
	public Boolean getSuspendedPrevScenarioFlag() {
		return suspendedPrevScenarioFlag;
	}
	public void setSuspendedPrevScenarioFlag(Boolean suspendedPrevScenarioFlag) {
		this.suspendedPrevScenarioFlag = suspendedPrevScenarioFlag;
	}
	
	
}
