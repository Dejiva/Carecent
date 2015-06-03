package com.codecoop.interact.core.dto.carepath;

public class DecisionDto {

    protected MetAnyDto metAny;
    protected MetNoneDto metNone;
   
    public MetAnyDto getMetAny() {
        return metAny;
    }
    public void setMetAny(MetAnyDto value) {
        this.metAny = value;
    }
    public MetNoneDto getMetNone() {
        return metNone;
    }
    public void setMetNone(MetNoneDto value) {
        this.metNone = value;
    }

}
