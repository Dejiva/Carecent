package com.codecoop.interact.core.dto.carepath;

public class CarePathDto {

    protected Long patientId;
    protected Long patientEpisodeId;

	protected StepsDto steps;
    protected String carePathCode;
    protected String carePathName;
    protected Integer stepOrder;
    protected String message;
    protected Integer stepId;
    protected String stepType;
    protected Integer firstStepId;
    

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Integer getStepId() {
	return stepId;
}
	public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}

	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}

public void setStepId(Integer stepId) {
	this.stepId = stepId;
}

	public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

	public Integer getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(Integer stepOrder) {
		this.stepOrder = stepOrder;
	}

	public StepsDto getSteps() {
    	if(this.steps == null){
    		this.steps = new StepsDto();
    	}
        return steps;
    }

    public void setSteps(StepsDto value) {
        this.steps = value;
    }

    public String getCarePathCode() {
        return carePathCode;
    }

    public void setCarePathCode(String value) {
        this.carePathCode = value;
    }

    public String getCarePathName() {
        return carePathName;
    }

    public void setCarePathName(String value) {
        this.carePathName = value;
    }

	public Integer getFirstStepId() {
		return firstStepId;
	}

	public void setFirstStepId(Integer firstStepId) {
		this.firstStepId = firstStepId;
	}

	public String getStepType() {
		return stepType;
	}

	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	
}
