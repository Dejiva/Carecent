package com.codecoop.interact.core.dto.carepath;

import java.util.ArrayList;
import java.util.List;


public class StepsDto {

    protected List<StepDto> step;
    public List<StepDto> getStep() {
        if (step == null) {
            step = new ArrayList<StepDto>();
        }
        return this.step;
    }

}
