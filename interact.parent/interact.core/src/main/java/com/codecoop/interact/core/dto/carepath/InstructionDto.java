package com.codecoop.interact.core.dto.carepath;

import java.util.ArrayList;
import java.util.List;


public class InstructionDto extends StepAttributeDto{

    protected List<String> detailDescription;

    public List<String> getDetailDescription() {
        if (detailDescription == null) {
            detailDescription = new ArrayList<String>();
        }
        return this.detailDescription;
    }

}
