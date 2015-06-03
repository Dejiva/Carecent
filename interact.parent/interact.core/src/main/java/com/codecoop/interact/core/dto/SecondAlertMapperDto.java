package com.codecoop.interact.core.dto;

import java.io.Serializable;

public class SecondAlertMapperDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1073133305807455480L;
	private Long id;
	private String scenarioCode;
	private String referenceTable;
	private String referenceColumn;
	private Long maxWaitTime;
	public SecondAlertMapperDto()
	{
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getScenarioCode() {
		return scenarioCode;
	}
	public void setScenarioCode(String scenarioCode) {
		this.scenarioCode = scenarioCode;
	}
	public String getReferenceTable() {
		return referenceTable;
	}
	public void setReferenceTable(String referenceTable) {
		this.referenceTable = referenceTable;
	}
	public String getReferenceColumn() {
		return referenceColumn;
	}
	public void setReferenceColumn(String referenceColumn) {
		this.referenceColumn = referenceColumn;
	}
	public Long getMaxWaitTime() {
		return maxWaitTime;
	}
	public void setMaxWaitTime(Long maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}
	
	
}
