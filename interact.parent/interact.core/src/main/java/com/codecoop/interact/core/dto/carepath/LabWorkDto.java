package com.codecoop.interact.core.dto.carepath;



public class LabWorkDto extends StepAttributeDto{

	protected String value;
    protected String datatype;
    protected String min;
    protected String max;
    protected String units;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String value) {
        this.datatype = value;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String value) {
        this.min = value;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String value) {
        this.max = value;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String value) {
        this.units = value;
    }

}
