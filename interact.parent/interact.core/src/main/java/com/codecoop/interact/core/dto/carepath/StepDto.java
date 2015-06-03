package com.codecoop.interact.core.dto.carepath;

import java.util.ArrayList;
import java.util.List;


public class StepDto {

    protected List<SymptomDto> symptom;
    protected List<VitalSignDto> vitalSign;
    protected List<LabWorkDto> labWork;
    protected List<LabResultDto> labResult;
    protected List<InstructionDto> instruction;
    protected DecisionDto decision;
    protected String stepName;
    protected Integer stepId;
    protected String stepType;
    protected String carePathName;
    protected String errorMsg;
    
    
    public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getCarePathName() {
		return carePathName;
	}
	public void setCarePathName(String carePathName) {
		this.carePathName = carePathName;
	}
	public List<SymptomDto> getSymptom() {
        if (symptom == null) {
            symptom = new ArrayList<SymptomDto>();
        }
        return this.symptom;
    }
    public List<VitalSignDto> getVitalSign() {
        if (vitalSign == null) {
            vitalSign = new ArrayList<VitalSignDto>();
        }
        return this.vitalSign;
    }
    public List<LabWorkDto> getLabWork() {
        if (labWork == null) {
            labWork = new ArrayList<LabWorkDto>();
        }
        return this.labWork;
    }
    public List<LabResultDto> getLabResult() {
        if (labResult == null) {
            labResult = new ArrayList<LabResultDto>();
        }
        return this.labResult;
    }
    public List<InstructionDto> getInstruction() {
        if (instruction == null) {
            instruction = new ArrayList<InstructionDto>();
        }
        return this.instruction;
    }
    public DecisionDto getDecision() {
    	if(this.decision == null){
    		this.decision = new DecisionDto();
    	}
        return decision;
    }
    public void setDecision(DecisionDto value) {
        this.decision = value;
    }
    public String getStepName() {
        return stepName;
    }
    public void setStepName(String value) {
        this.stepName = value;
    }
    public Integer getStepId() {
        return stepId;
    }
    public void setStepId(Integer value) {
        this.stepId = value;
    }
    public String getStepType() {
        return stepType;
    }
    public void setStepType(String value) {
        this.stepType = value;
    }

}
