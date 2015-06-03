package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALERTS_TO_OTHERSTAFF", catalog = "INTERACT")
public class AlertsToOtherStaff  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -147545148405256968L;
	private Long id;
	private Long AlertsToAssignId;
	private String ToOtherStaff;
	private String AlertTemplateOtherStaff;
	private Boolean suspendedPrevScenarioFlag;
	public AlertsToOtherStaff()
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
	@Column(name = "ALERTS_TO_ASSIGNED_ID")
	public Long getAlertsToAssignId() {
		return AlertsToAssignId;
	}
	public void setAlertsToAssignId(Long alertsToAssignId) {
		AlertsToAssignId = alertsToAssignId;
	}
	@Column(name = "TO_OTHER_STAFF",length=50)
	public String getToOtherStaff() {
		return ToOtherStaff;
	}
	public void setToOtherStaff(String toOtherStaff) {
		ToOtherStaff = toOtherStaff;
	}
	
	@Column(name = "ALERT_TEMPLATE_OTHERSTAFF",length=500)
	public String getAlertTemplateOtherStaff() {
		return AlertTemplateOtherStaff;
	}
	public void setAlertTemplateOtherStaff(String alertTemplateOtherStaff) {
		AlertTemplateOtherStaff = alertTemplateOtherStaff;
	}
	@Column(name ="SUSPENDED_PREV_SCENARIO_FLAG")
	public Boolean getSuspendedPrevScenarioFlag() {
		return suspendedPrevScenarioFlag;
	}
	public void setSuspendedPrevScenarioFlag(Boolean suspendedPrevScenarioFlag) {
		this.suspendedPrevScenarioFlag = suspendedPrevScenarioFlag;
	}

}
