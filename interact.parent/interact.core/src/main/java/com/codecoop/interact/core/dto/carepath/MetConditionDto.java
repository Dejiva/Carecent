package com.codecoop.interact.core.dto.carepath;

import java.io.Serializable;

public abstract class MetConditionDto implements Serializable {
    protected Integer nextStepId;
    protected String message;

    public Integer getNextStepId() {
        return nextStepId;
    }

    public void setNextStepId(Integer value) {
        this.nextStepId = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

}
