package com.codecoop.interact.core.dto.carepath;

import java.io.Serializable;

public abstract class StepAttributeDto implements Serializable{

	private static final long serialVersionUID = -5999456430987005882L;
	protected String name;
    protected String carePathAttrCode;
    protected boolean exists;
    protected boolean isFromNextCP;
    
	
	public boolean isFromNextCP() {
		return isFromNextCP;
	}
	public void setFromNextCP(boolean isFromNextCP) {
		this.isFromNextCP = isFromNextCP;
	}
	public boolean isExists() {
		return exists;
	}
	public void setExists(boolean exists) {
		this.exists = exists;
	}    
    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }
    public String getCarePathAttrCode() {
        return carePathAttrCode;
    }
    public void setCarePathAttrCode(String value) {
        this.carePathAttrCode = value;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StepAttributeDto other = (StepAttributeDto) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
