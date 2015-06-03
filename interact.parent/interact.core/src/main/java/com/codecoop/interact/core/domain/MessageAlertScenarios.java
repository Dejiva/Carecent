package com.codecoop.interact.core.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "MESSAGE_ALERT_SCENARIOS", catalog = "INTERACT")
public class MessageAlertScenarios implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1093689714977279956L;
	private Long id;
	private String code;
	private String description;
	private String messageTemplate;
	private Boolean suspendedPrevScenarioFlag;
	private Long maxWaitTime;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "CODE", length = 100)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "MESSAGE_TEMPLATE", length = 500)
	public String getMessageTemplate() {
		return messageTemplate;
	}
	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}
	@Column(name ="SUSPENDED_PREV_SCENARIO_FLAG")
	public Boolean getSuspendedPrevScenarioFlag() {
		return suspendedPrevScenarioFlag;
	}
	public void setSuspendedPrevScenarioFlag(Boolean suspendedPrevScenarioFlag) {
		this.suspendedPrevScenarioFlag = suspendedPrevScenarioFlag;
	}
	@Column(name ="MAX_WAIT_TIME",length = 25)
	public Long getMaxWaitTime() {
		return maxWaitTime;
	}
	public void setMaxWaitTime(Long maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

}
