package com.codecoop.interact.core.dto.carepath;

import java.util.ArrayList;
import java.util.List;

public class SymptomDto extends StepAttributeDto{

	private static final long serialVersionUID = -3927744264282743080L;
	private List<NextCarePathDto> nextCarePath;

	public List<NextCarePathDto> getNextCarePath() {
		if(this.nextCarePath == null){
			return new ArrayList<NextCarePathDto>();
		}
			
		return nextCarePath;
	}

	public void setNextCarePath(List<NextCarePathDto> nextCarePath) {
		this.nextCarePath = nextCarePath;
	}
//	@Override
//    public int hashCode() {
//        return (this.name.hashCode() );
//	}
//    @Override
//    public boolean equals(Object obj) {
//    	if(obj != null){
//    		return this.name.equalsIgnoreCase(((SymptomDto)obj).getName());
//    	}
//    	return false;
//    }
}
