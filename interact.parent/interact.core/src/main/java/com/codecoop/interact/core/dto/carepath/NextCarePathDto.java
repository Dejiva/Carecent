package com.codecoop.interact.core.dto.carepath;


public class NextCarePathDto {
    protected boolean stepFlag;
    protected String carePathCode;
    protected Integer stepId;

    public boolean isStepFlag() {
        return stepFlag;
    }

    public void setStepFlag(boolean value) {
        this.stepFlag = value;
    }

    public String getCarePathCode() {
        return carePathCode;
    }

    public void setCarePathCode(String value) {
        this.carePathCode = value;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer value) {
        this.stepId = value;
    }

}
